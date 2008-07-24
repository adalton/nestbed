import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import net.tinyos.message.Message;
import net.tinyos.message.MessageListener;
import net.tinyos.message.MoteIF;
import net.tinyos.packet.BuildSource;
import net.tinyos.packet.PacketSource;
import net.tinyos.packet.PhoenixSource;


public class TraceReceiver implements MessageListener {
    private final static TraceEntry   TRACE_ENTRY   = new TraceEntry();
    private final static TraceMessage TRACE_MESSAGE = new TraceMessage();

    private String          connectionString;
    private PacketSource    packetSource;
    private PhoenixSource   phoenixSource;
    private MoteIF          moteIf;
    private String          traceFilename;
    private List<TraceData> traceMessages = new ArrayList<TraceData>();
    private int             messageCount  = 0;


    public TraceReceiver(String connectionString, String traceFilename) {
        this.connectionString = connectionString;
        this.traceFilename    = traceFilename;
    }


    public void start() {
        packetSource  = BuildSource.makePacketSource(connectionString);
        phoenixSource = BuildSource.makePhoenix(packetSource, null);
        moteIf        = new MoteIF(phoenixSource);

        moteIf.registerListener(TRACE_ENTRY,   this);
        moteIf.registerListener(TRACE_MESSAGE, this);
    }


    public void send(Message message) throws IOException {
        moteIf.send(0, message);
    }


    public void messageReceived(int toAddr, Message message) {
        if (message instanceof TraceEntry) {
            TraceEntry entry = (TraceEntry) message;

            if ((entry.get_type() == 0 || entry.get_type() == 1) && (entry.get_u_callTrace_functionId() == 0 || entry.get_u_callTrace_moduleId() == 0)) {
                System.out.println("Bad message");
                System.out.println("-----------------------------------------");
                System.out.println(entry);
                System.out.println("-----------------------------------------");
            } else {
                messageCount++;
                TraceData traceData = null;

                if        (entry.get_type() == 0 || entry.get_type() == 1) {
                    traceData = new FunctionTraceData(entry.get_type(), entry.get_u_callTrace_moduleId(), entry.get_u_callTrace_functionId());
                } else if (entry.get_type() == 2 || entry.get_type() == 3) {
                    // Make this get the address and magic
                    traceData = new RadioTraceData(entry.get_type(), entry.get_u_radioTrace_address(), entry.get_u_radioTrace_magic());
                }

                System.out.println(traceData);
                traceMessages.add(traceData);
            }
        } else if (message instanceof TraceMessage) {
            //
            // TraceMessage signals the end of the message stream
            //
            moteIf.deregisterListener(TRACE_ENTRY,   this);
            moteIf.deregisterListener(TRACE_MESSAGE, this);

            try {
                phoenixSource.shutdown();
                packetSource.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try { 
                    ObjectOutputStream  traceOut = new ObjectOutputStream(
                                           new FileOutputStream(traceFilename));
                    traceOut.writeObject(traceMessages);
                    traceOut.close();
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                System.out.printf("Message count: %d\n", messageCount);
                System.exit(0);
            }
        }
    }


    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: TraceReceiver <connectionString> " +
                               "<traceOutputFilename>");
            System.exit(1);
        }
        String connectionString    = args[0];
        String traceOutputFilename = args[1];

        TraceReceiver tr = new TraceReceiver(connectionString,
                                             traceOutputFilename);

        TraceMessage message = new TraceMessage();

        tr.start();
        tr.send(message);
    }
}
