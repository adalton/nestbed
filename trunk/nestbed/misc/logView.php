<!-- $Id:$ -->
<html>
<head>
    <title>NESTBed Server Log Viewer</title>
    <style type="text/css">
        .DEBUG {
            font-weight:       bold;
        }

        .DEBUG a {
            text-decoration:   none;
            color:             black;
        }

        .DEBUG a:visited {
            color:             black;
        }

        .DEBUG a:hover {
            text-decoration:   underline;
            color:             black;
        }

        .INFO {
            font-weight:       bold;
            color:             blue;
        }

        .INFO a {
            text-decoration:   none;
            color:             blue;
        }

        .INFO a:visited {
            color:             blue;
        }

        .INFO a:hover {
            text-decoration:   underline;
            color:             blue;
        }
        
        .WARN {
            font-weight:       bold;
            color:             orange;
        }

        .WARN a {
            text-decoration:   none;
            color:             orange;
        }

        .WARN a:visited {
            color:             orange;
        }

        .WARN a:hover {
            text-decoration:   underline;
            color:             orange;
        }

        .ERROR {
            font-weight:       bold;
            color:             red;
        }

        .ERROR a {
            text-decoration:   none;
            color:             red;
        }

        .ERROR a:visited {
            color:             red;
        }

        .ERROR a:hover {
            text-decoration:   underline;
            color:             red;
        }
        
        .FATAL {
            font-weight:       bold;
            color:             red;
        }

        .FATAL a {
            text-decoration:   blink;
            color:             red;
        }

        .FATAL a:visited {
            text-decoration:   blink;
            color:             red;
        }

        .FATAL a:hover {
            text-decoration:   underline;
            color:             red;
        }

        .logMessageBox {
            border-left:       ridge;
            margin-left:       15px;
        }

        .logMessage {
            font-family:       monospace, sans-serif;
            margin-left:       15px;
        }
    </style>
</head>
<body>
<hr/>
    <h1><i>NESTBed</i> Server Log Viewer</h1>
<hr/>
<?php

    function severityToLevel($severity) {
        $level = 0;

        if ($severity == "DEBUG") {
            $level = 0;
        } else if ($severity == "INFO") {
            $level = 1;
        } else if ($severity == "WARN") {
            $level = 2;
        } else if ($severity == "ERROR") {
            $level = 3;
        } else if ($severity == "FATAL") {
            $level = 4;
        }

        return $level;
    }


    $fileName        = "./serverLog.txt";
    $desiredSeverity = "DEBUG";
    $desiredClass    = "";

    if ($_GET["severity"] != "") {
        $desiredSeverity = $_GET["severity"];
    }

    if ($_GET["class"] != "") {
        $desiredClass    = $_GET["class"];
    }

    if ($_GET["filename"] != "") {
        $fileName = $_GET["filename"];
    }

    $first         = true;
    $severityLevel = severityToLevel($desiredSeverity);
    $lines         = file($fileName);
    $inLogMsg      = false;

    foreach ($lines as $line) {
        $pos = strpos($line, "%log%");
        if ($pos === 0) {
            $line = substr($line, $pos + 5);

            if ($inLogMsg) {
                if (($level >= $severityLevel) || ($class == $desiredClass)) {
                    echo "    </div>\n";
                    echo "    </div>\n";
                    echo "    </td>\n";
                    echo "</tr>\n";
                    echo "</table>\n";
                    echo "<p/>\n";
                    echo "<hr/>\n";
                }
            }
            $state    = 'HEADER';
            $inLogMsg = true;
        }

        if ($state == 'HEADER') {
            $date     = trim(strtok($line, "|"));
            $severity = trim(strtok("|"));
            $class    = trim(strtok("|"));
            $level    = severityToLevel($severity);

            if ($first) {
                echo "Log begins at:  $date<br/>\n<hr/>\n";
                $first = false;
            }

            if (($level >= $severityLevel) || ($class == $desiredClass)) {
                echo "<table border=\"0\" width=\"100%\">\n";
                echo "<tr>\n";
                echo "    <th align=\"left\" width=\"10%\">Date:</th>\n";
                echo "    <td>$date</td>\n";
                echo "<tr>\n";
                echo "</tr>\n";
                echo "    <th align=\"left\">Severity:</th>\n";
                echo "    <td class=\"$severity\"><a href=\"./logView.php?severity=$severity\">$severity</a></td>\n";
                echo "<tr>\n";
                echo "</tr>\n";
                echo "    <th align=\"left\">Class:</th>\n";
                echo "    <td>$class</td>\n";
                echo "</tr>\n";
                echo "<tr>\n";
                echo "    <td colspan=\"2\" >\n";
                echo "    <div class=\"logMessageBox\">\n";
                echo "    <div class=\"logMessage\">\n";
            }

            $state = 'BODY';
        } else {
            if (($level >= $severityLevel) || ($class == $desiredClass)) {
                echo "$line<br/>\n";
            }
        }
    }

    if ($inLogMsg) {
        if (($level >= $severityLevel) || ($class == $desiredClass)) {
            echo "    </div>\n";
            echo "    </div>\n";
            echo "    </td>\n";
            echo "</tr>\n";
            echo "</table>\n";
            echo "<p/>\n";
            echo "<hr/>\n";
        }
    }
    echo "Log ends at: $date<br/>\n";
?>
</body>
</html>
