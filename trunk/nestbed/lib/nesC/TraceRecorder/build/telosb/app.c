#define nx_struct struct
#define nx_union union
#define dbg(mode, format, ...) ((void)0)
#define dbg_clear(mode, format, ...) ((void)0)
#define dbg_active(mode) 0
# 38 "/opt/msp430/msp430/include/sys/inttypes.h"
typedef signed char int8_t;
typedef unsigned char uint8_t;

typedef int int16_t;
typedef unsigned int uint16_t;

typedef long int32_t;
typedef unsigned long uint32_t;

typedef long long int64_t;
typedef unsigned long long uint64_t;




typedef int16_t intptr_t;
typedef uint16_t uintptr_t;
# 140 "/opt/nesc-1.2.7a/lib/ncc/nesc_nx.h"
static __inline uint8_t __nesc_ntoh_uint8(const void *source);




static __inline uint8_t __nesc_hton_uint8(void *target, uint8_t value);
#line 169
static __inline uint16_t __nesc_ntoh_uint16(const void *source);




static __inline uint16_t __nesc_hton_uint16(void *target, uint16_t value);
#line 290
typedef struct { char data[1]; } __attribute__((packed)) nx_int8_t;typedef int8_t __nesc_nxbase_nx_int8_t  ;
typedef struct { char data[2]; } __attribute__((packed)) nx_int16_t;typedef int16_t __nesc_nxbase_nx_int16_t  ;
typedef struct { char data[4]; } __attribute__((packed)) nx_int32_t;typedef int32_t __nesc_nxbase_nx_int32_t  ;
typedef struct { char data[8]; } __attribute__((packed)) nx_int64_t;typedef int64_t __nesc_nxbase_nx_int64_t  ;
typedef struct { char data[1]; } __attribute__((packed)) nx_uint8_t;typedef uint8_t __nesc_nxbase_nx_uint8_t  ;
typedef struct { char data[2]; } __attribute__((packed)) nx_uint16_t;typedef uint16_t __nesc_nxbase_nx_uint16_t  ;
typedef struct { char data[4]; } __attribute__((packed)) nx_uint32_t;typedef uint32_t __nesc_nxbase_nx_uint32_t  ;
typedef struct { char data[8]; } __attribute__((packed)) nx_uint64_t;typedef uint64_t __nesc_nxbase_nx_uint64_t  ;



typedef struct { char data[1]; } __attribute__((packed)) nxle_int8_t;typedef int8_t __nesc_nxbase_nxle_int8_t  ;
typedef struct { char data[2]; } __attribute__((packed)) nxle_int16_t;typedef int16_t __nesc_nxbase_nxle_int16_t  ;
typedef struct { char data[4]; } __attribute__((packed)) nxle_int32_t;typedef int32_t __nesc_nxbase_nxle_int32_t  ;
typedef struct { char data[8]; } __attribute__((packed)) nxle_int64_t;typedef int64_t __nesc_nxbase_nxle_int64_t  ;
typedef struct { char data[1]; } __attribute__((packed)) nxle_uint8_t;typedef uint8_t __nesc_nxbase_nxle_uint8_t  ;
typedef struct { char data[2]; } __attribute__((packed)) nxle_uint16_t;typedef uint16_t __nesc_nxbase_nxle_uint16_t  ;
typedef struct { char data[4]; } __attribute__((packed)) nxle_uint32_t;typedef uint32_t __nesc_nxbase_nxle_uint32_t  ;
typedef struct { char data[8]; } __attribute__((packed)) nxle_uint64_t;typedef uint64_t __nesc_nxbase_nxle_uint64_t  ;
# 151 "/opt/msp430/lib/gcc-lib/msp430/3.2.3/include/stddef.h" 3
typedef int ptrdiff_t;
#line 213
typedef unsigned int size_t;
#line 325
typedef int wchar_t;
# 41 "/opt/msp430/msp430/include/sys/types.h"
typedef unsigned char u_char;
typedef unsigned short u_short;
typedef unsigned int u_int;
typedef unsigned long u_long;
typedef unsigned short ushort;
typedef unsigned int uint;

typedef uint8_t u_int8_t;
typedef uint16_t u_int16_t;
typedef uint32_t u_int32_t;
typedef uint64_t u_int64_t;

typedef u_int64_t u_quad_t;
typedef int64_t quad_t;
typedef quad_t *qaddr_t;

typedef char *caddr_t;
typedef const char *c_caddr_t;
typedef volatile char *v_caddr_t;
typedef u_int32_t fixpt_t;
typedef u_int32_t gid_t;
typedef u_int32_t in_addr_t;
typedef u_int16_t in_port_t;
typedef u_int32_t ino_t;
typedef long key_t;
typedef u_int16_t mode_t;
typedef u_int16_t nlink_t;
typedef quad_t rlim_t;
typedef int32_t segsz_t;
typedef int32_t swblk_t;
typedef int32_t ufs_daddr_t;
typedef int32_t ufs_time_t;
typedef u_int32_t uid_t;
# 40 "/opt/msp430/msp430/include/string.h"
extern void *memcpy(void *, const void *, size_t );

extern void *memset(void *, int , size_t );
#line 63
extern void *memset(void *, int , size_t );
# 59 "/opt/msp430/msp430/include/stdlib.h"
#line 56
typedef struct __nesc_unnamed4242 {
  int quot;
  int rem;
} div_t;







#line 64
typedef struct __nesc_unnamed4243 {
  long quot;
  long rem;
} ldiv_t;
# 122 "/opt/msp430/msp430/include/sys/config.h" 3
typedef long int __int32_t;
typedef unsigned long int __uint32_t;
# 12 "/opt/msp430/msp430/include/sys/_types.h"
typedef long _off_t;
typedef long _ssize_t;
# 28 "/opt/msp430/msp430/include/sys/reent.h" 3
typedef __uint32_t __ULong;


struct _glue {

  struct _glue *_next;
  int _niobs;
  struct __sFILE *_iobs;
};

struct _Bigint {

  struct _Bigint *_next;
  int _k, _maxwds, _sign, _wds;
  __ULong _x[1];
};


struct __tm {

  int __tm_sec;
  int __tm_min;
  int __tm_hour;
  int __tm_mday;
  int __tm_mon;
  int __tm_year;
  int __tm_wday;
  int __tm_yday;
  int __tm_isdst;
};







struct _atexit {
  struct _atexit *_next;
  int _ind;
  void (*_fns[32])(void );
};








struct __sbuf {
  unsigned char *_base;
  int _size;
};






typedef long _fpos_t;
#line 116
struct __sFILE {
  unsigned char *_p;
  int _r;
  int _w;
  short _flags;
  short _file;
  struct __sbuf _bf;
  int _lbfsize;


  void *_cookie;

  int (*_read)(void *_cookie, char *_buf, int _n);
  int (*_write)(void *_cookie, const char *_buf, int _n);

  _fpos_t (*_seek)(void *_cookie, _fpos_t _offset, int _whence);
  int (*_close)(void *_cookie);


  struct __sbuf _ub;
  unsigned char *_up;
  int _ur;


  unsigned char _ubuf[3];
  unsigned char _nbuf[1];


  struct __sbuf _lb;


  int _blksize;
  int _offset;

  struct _reent *_data;
};
#line 174
struct _rand48 {
  unsigned short _seed[3];
  unsigned short _mult[3];
  unsigned short _add;
};









struct _reent {


  int _errno;




  struct __sFILE *_stdin, *_stdout, *_stderr;

  int _inc;
  char _emergency[25];

  int _current_category;
  const char *_current_locale;

  int __sdidinit;

  void (*__cleanup)(struct _reent *);


  struct _Bigint *_result;
  int _result_k;
  struct _Bigint *_p5s;
  struct _Bigint **_freelist;


  int _cvtlen;
  char *_cvtbuf;

  union __nesc_unnamed4244 {

    struct __nesc_unnamed4245 {

      unsigned int _unused_rand;
      char *_strtok_last;
      char _asctime_buf[26];
      struct __tm _localtime_buf;
      int _gamma_signgam;
      __extension__ unsigned long long _rand_next;
      struct _rand48 _r48;
    } _reent;



    struct __nesc_unnamed4246 {


      unsigned char *_nextf[30];
      unsigned int _nmalloc[30];
    } _unused;
  } _new;


  struct _atexit *_atexit;
  struct _atexit _atexit0;


  void (**_sig_func)(int );




  struct _glue __sglue;
  struct __sFILE __sf[3];
};
#line 273
struct _reent;
# 18 "/opt/msp430/msp430/include/math.h"
union __dmath {

  __uint32_t i[2];
  double d;
};




union __dmath;
#line 208
struct exception {


  int type;
  char *name;
  double arg1;
  double arg2;
  double retval;
  int err;
};
#line 261
enum __fdlibm_version {

  __fdlibm_ieee = -1, 
  __fdlibm_svid, 
  __fdlibm_xopen, 
  __fdlibm_posix
};




enum __fdlibm_version;
# 20 "/opt/tinyos-2.x/tos/system/tos.h"
typedef uint8_t bool;
enum __nesc_unnamed4247 {
#line 21
  FALSE = 0, TRUE = 1
};
typedef nx_int8_t nx_bool;






struct __nesc_attr_atmostonce {
};
#line 31
struct __nesc_attr_atleastonce {
};
#line 32
struct __nesc_attr_exactlyonce {
};
# 34 "/opt/tinyos-2.x/tos/types/TinyError.h"
enum __nesc_unnamed4248 {
  SUCCESS = 0, 
  FAIL = 1, 
  ESIZE = 2, 
  ECANCEL = 3, 
  EOFF = 4, 
  EBUSY = 5, 
  EINVAL = 6, 
  ERETRY = 7, 
  ERESERVE = 8, 
  EALREADY = 9
};

typedef uint8_t error_t  ;

static inline error_t ecombine(error_t r1, error_t r2);
# 39 "/opt/msp430/msp430/include/msp430/iostructures.h"
#line 27
typedef union port {
  volatile unsigned char reg_p;
  volatile struct __nesc_unnamed4249 {
    unsigned char __p0 : 1, 
    __p1 : 1, 
    __p2 : 1, 
    __p3 : 1, 
    __p4 : 1, 
    __p5 : 1, 
    __p6 : 1, 
    __p7 : 1;
  } __pin;
} __attribute((packed))  ioregister_t;
# 106 "/opt/msp430/msp430/include/msp430/iostructures.h" 3
struct port_full_t {
  ioregister_t in;
  ioregister_t out;
  ioregister_t dir;
  ioregister_t ifg;
  ioregister_t ies;
  ioregister_t ie;
  ioregister_t sel;
};




struct port_simple_t {
  ioregister_t in;
  ioregister_t out;
  ioregister_t dir;
  ioregister_t sel;
};




struct port_full_t;



struct port_full_t;



struct port_simple_t;



struct port_simple_t;



struct port_simple_t;



struct port_simple_t;
# 103 "/opt/msp430/msp430/include/msp430/gpio.h" 3
volatile unsigned char P1OUT __asm ("0x0021");

volatile unsigned char P1DIR __asm ("0x0022");





volatile unsigned char P1IE __asm ("0x0025");

volatile unsigned char P1SEL __asm ("0x0026");






volatile unsigned char P2OUT __asm ("0x0029");

volatile unsigned char P2DIR __asm ("0x002A");





volatile unsigned char P2IE __asm ("0x002D");

volatile unsigned char P2SEL __asm ("0x002E");






volatile unsigned char P3OUT __asm ("0x0019");

volatile unsigned char P3DIR __asm ("0x001A");

volatile unsigned char P3SEL __asm ("0x001B");






volatile unsigned char P4OUT __asm ("0x001D");

volatile unsigned char P4DIR __asm ("0x001E");

volatile unsigned char P4SEL __asm ("0x001F");






volatile unsigned char P5OUT __asm ("0x0031");

volatile unsigned char P5DIR __asm ("0x0032");

volatile unsigned char P5SEL __asm ("0x0033");






volatile unsigned char P6OUT __asm ("0x0035");

volatile unsigned char P6DIR __asm ("0x0036");

volatile unsigned char P6SEL __asm ("0x0037");
# 85 "/opt/msp430/msp430/include/msp430/usart.h"
volatile unsigned char U0CTL __asm ("0x0070");

volatile unsigned char U0TCTL __asm ("0x0071");



volatile unsigned char U0MCTL __asm ("0x0073");

volatile unsigned char U0BR0 __asm ("0x0074");

volatile unsigned char U0BR1 __asm ("0x0075");

volatile unsigned char U0RXBUF __asm ("0x0076");
#line 254
volatile unsigned char U1CTL __asm ("0x0078");

volatile unsigned char U1TCTL __asm ("0x0079");



volatile unsigned char U1MCTL __asm ("0x007B");

volatile unsigned char U1BR0 __asm ("0x007C");

volatile unsigned char U1BR1 __asm ("0x007D");

volatile unsigned char U1RXBUF __asm ("0x007E");
# 24 "/opt/msp430/msp430/include/msp430/timera.h"
volatile unsigned int TA0CTL __asm ("0x0160");

volatile unsigned int TA0CCTL0 __asm ("0x0162");

volatile unsigned int TA0CCTL1 __asm ("0x0164");

volatile unsigned int TA0CCTL2 __asm ("0x0166");

volatile unsigned int TA0R __asm ("0x0170");
# 114 "/opt/msp430/msp430/include/msp430/timera.h" 3
#line 105
typedef struct __nesc_unnamed4250 {
  volatile unsigned 
  taifg : 1, 
  taie : 1, 
  taclr : 1, 
  dummy : 1, 
  tamc : 2, 
  taid : 2, 
  tassel : 2;
} __attribute((packed))  tactl_t;
#line 130
#line 116
typedef struct __nesc_unnamed4251 {
  volatile unsigned 
  ccifg : 1, 
  cov : 1, 
  out : 1, 
  cci : 1, 
  ccie : 1, 
  outmod : 3, 
  cap : 1, 
  dummy : 1, 
  scci : 1, 
  scs : 1, 
  ccis : 2, 
  cm : 2;
} __attribute((packed))  tacctl_t;


struct timera_t {
  tactl_t ctl;
  tacctl_t cctl0;
  tacctl_t cctl1;
  tacctl_t cctl2;
  volatile unsigned dummy[4];
  volatile unsigned tar;
  volatile unsigned taccr0;
  volatile unsigned taccr1;
  volatile unsigned taccr2;
};



struct timera_t;
# 26 "/opt/msp430/msp430/include/msp430/timerb.h"
volatile unsigned int TBCCTL0 __asm ("0x0182");





volatile unsigned int TBR __asm ("0x0190");

volatile unsigned int TBCCR0 __asm ("0x0192");
#line 75
#line 63
typedef struct __nesc_unnamed4252 {
  volatile unsigned 
  tbifg : 1, 
  tbie : 1, 
  tbclr : 1, 
  dummy1 : 1, 
  tbmc : 2, 
  tbid : 2, 
  tbssel : 2, 
  dummy2 : 1, 
  tbcntl : 2, 
  tbclgrp : 2;
} __attribute((packed))  tbctl_t;
#line 90
#line 77
typedef struct __nesc_unnamed4253 {
  volatile unsigned 
  ccifg : 1, 
  cov : 1, 
  out : 1, 
  cci : 1, 
  ccie : 1, 
  outmod : 3, 
  cap : 1, 
  clld : 2, 
  scs : 1, 
  ccis : 2, 
  cm : 2;
} __attribute((packed))  tbcctl_t;


struct timerb_t {
  tbctl_t ctl;
  tbcctl_t cctl0;
  tbcctl_t cctl1;
  tbcctl_t cctl2;

  tbcctl_t cctl3;
  tbcctl_t cctl4;
  tbcctl_t cctl5;
  tbcctl_t cctl6;



  volatile unsigned tbr;
  volatile unsigned tbccr0;
  volatile unsigned tbccr1;
  volatile unsigned tbccr2;

  volatile unsigned tbccr3;
  volatile unsigned tbccr4;
  volatile unsigned tbccr5;
  volatile unsigned tbccr6;
};





struct timerb_t;
# 20 "/opt/msp430/msp430/include/msp430/basic_clock.h"
volatile unsigned char DCOCTL __asm ("0x0056");

volatile unsigned char BCSCTL1 __asm ("0x0057");

volatile unsigned char BCSCTL2 __asm ("0x0058");
# 18 "/opt/msp430/msp430/include/msp430/adc12.h"
volatile unsigned int ADC12CTL0 __asm ("0x01A0");

volatile unsigned int ADC12CTL1 __asm ("0x01A2");
#line 42
#line 30
typedef struct __nesc_unnamed4254 {
  volatile unsigned 
  adc12sc : 1, 
  enc : 1, 
  adc12tovie : 1, 
  adc12ovie : 1, 
  adc12on : 1, 
  refon : 1, 
  r2_5v : 1, 
  msc : 1, 
  sht0 : 4, 
  sht1 : 4;
} __attribute((packed))  adc12ctl0_t;
#line 54
#line 44
typedef struct __nesc_unnamed4255 {
  volatile unsigned 
  adc12busy : 1, 
  conseq : 2, 
  adc12ssel : 2, 
  adc12div : 3, 
  issh : 1, 
  shp : 1, 
  shs : 2, 
  cstartadd : 4;
} __attribute((packed))  adc12ctl1_t;
#line 74
#line 56
typedef struct __nesc_unnamed4256 {
  volatile unsigned 
  bit0 : 1, 
  bit1 : 1, 
  bit2 : 1, 
  bit3 : 1, 
  bit4 : 1, 
  bit5 : 1, 
  bit6 : 1, 
  bit7 : 1, 
  bit8 : 1, 
  bit9 : 1, 
  bit10 : 1, 
  bit11 : 1, 
  bit12 : 1, 
  bit13 : 1, 
  bit14 : 1, 
  bit15 : 1;
} __attribute((packed))  adc12xflg_t;


struct adc12_t {
  adc12ctl0_t ctl0;
  adc12ctl1_t ctl1;
  adc12xflg_t ifg;
  adc12xflg_t ie;
  adc12xflg_t iv;
};




struct adc12_t;
# 71 "/opt/msp430/msp430/include/msp430x16x.h"
volatile unsigned char ME1 __asm ("0x0004");





volatile unsigned char ME2 __asm ("0x0005");
# 142 "/opt/tinyos-2.x/tos/chips/msp430/msp430hardware.h"
 static volatile uint8_t U0CTLnr __asm ("0x0070");
 static volatile uint8_t I2CTCTLnr __asm ("0x0071");
 static volatile uint8_t I2CDCTLnr __asm ("0x0072");
#line 177
typedef uint8_t mcu_power_t  ;
static inline mcu_power_t mcombine(mcu_power_t m1, mcu_power_t m2);


enum __nesc_unnamed4257 {
  MSP430_POWER_ACTIVE = 0, 
  MSP430_POWER_LPM0 = 1, 
  MSP430_POWER_LPM1 = 2, 
  MSP430_POWER_LPM2 = 3, 
  MSP430_POWER_LPM3 = 4, 
  MSP430_POWER_LPM4 = 5
};

static inline void __nesc_disable_interrupt(void );





static inline void __nesc_enable_interrupt(void );




typedef bool __nesc_atomic_t;
__nesc_atomic_t __nesc_atomic_start(void );
void __nesc_atomic_end(__nesc_atomic_t reenable_interrupts);






__nesc_atomic_t __nesc_atomic_start(void )  ;







void __nesc_atomic_end(__nesc_atomic_t reenable_interrupts)  ;
# 33 "/opt/tinyos-2.x/tos/platforms/telosb/hardware.h"
static inline void TOSH_SET_SIMO0_PIN(void);
#line 33
static inline void TOSH_CLR_SIMO0_PIN(void);
#line 33
static inline void TOSH_MAKE_SIMO0_OUTPUT(void);
static inline void TOSH_SET_UCLK0_PIN(void);
#line 34
static inline void TOSH_CLR_UCLK0_PIN(void);
#line 34
static inline void TOSH_MAKE_UCLK0_OUTPUT(void);
#line 76
enum __nesc_unnamed4258 {

  TOSH_HUMIDITY_ADDR = 5, 
  TOSH_HUMIDTEMP_ADDR = 3, 
  TOSH_HUMIDITY_RESET = 0x1E
};



static inline void TOSH_SET_FLASH_CS_PIN(void);
#line 85
static inline void TOSH_CLR_FLASH_CS_PIN(void);
#line 85
static inline void TOSH_MAKE_FLASH_CS_OUTPUT(void);
static inline void TOSH_SET_FLASH_HOLD_PIN(void);
#line 86
static inline void TOSH_MAKE_FLASH_HOLD_OUTPUT(void);
# 41 "/opt/tinyos-2.x/tos/types/Storage.h"
typedef uint8_t volume_id_t;
typedef uint32_t storage_addr_t;
typedef uint32_t storage_len_t;
typedef uint32_t storage_cookie_t;

enum __nesc_unnamed4259 {
  SEEK_BEGINNING = 0
};
# 40 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25p.h"
typedef storage_addr_t stm25p_addr_t;
typedef storage_len_t stm25p_len_t;

enum __nesc_unnamed4260 {
  STM25P_NUM_SECTORS = 16, 
  STM25P_SECTOR_SIZE_LOG2 = 16, 
  STM25P_SECTOR_SIZE = 1L << STM25P_SECTOR_SIZE_LOG2, 
  STM25P_SECTOR_MASK = 0xffff, 
  STM25P_PAGE_SIZE_LOG2 = 8, 
  STM25P_PAGE_SIZE = 1 << STM25P_PAGE_SIZE_LOG2, 
  STM25P_PAGE_MASK = STM25P_PAGE_SIZE - 1, 
  STM25P_INVALID_ADDRESS = 0xffffffff
};




#line 54
typedef struct stm25p_volume_info_t {
  uint8_t base;
  uint8_t size;
} stm25p_volume_info_t;
# 8 "build/telosb/StorageVolumes.h"
static const stm25p_volume_info_t STM25P_VMAP[1] = { 
{ .base = 0, .size = 4 } };
# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.h"
enum __nesc_unnamed4261 {
  MSP430TIMER_CM_NONE = 0, 
  MSP430TIMER_CM_RISING = 1, 
  MSP430TIMER_CM_FALLING = 2, 
  MSP430TIMER_CM_BOTH = 3, 

  MSP430TIMER_STOP_MODE = 0, 
  MSP430TIMER_UP_MODE = 1, 
  MSP430TIMER_CONTINUOUS_MODE = 2, 
  MSP430TIMER_UPDOWN_MODE = 3, 

  MSP430TIMER_TACLK = 0, 
  MSP430TIMER_TBCLK = 0, 
  MSP430TIMER_ACLK = 1, 
  MSP430TIMER_SMCLK = 2, 
  MSP430TIMER_INCLK = 3, 

  MSP430TIMER_CLOCKDIV_1 = 0, 
  MSP430TIMER_CLOCKDIV_2 = 1, 
  MSP430TIMER_CLOCKDIV_4 = 2, 
  MSP430TIMER_CLOCKDIV_8 = 3
};
#line 64
#line 51
typedef struct __nesc_unnamed4262 {

  int ccifg : 1;
  int cov : 1;
  int out : 1;
  int cci : 1;
  int ccie : 1;
  int outmod : 3;
  int cap : 1;
  int clld : 2;
  int scs : 1;
  int ccis : 2;
  int cm : 2;
} msp430_compare_control_t;
#line 76
#line 66
typedef struct __nesc_unnamed4263 {

  int taifg : 1;
  int taie : 1;
  int taclr : 1;
  int _unused0 : 1;
  int mc : 2;
  int id : 2;
  int tassel : 2;
  int _unused1 : 6;
} msp430_timer_a_control_t;
#line 91
#line 78
typedef struct __nesc_unnamed4264 {

  int tbifg : 1;
  int tbie : 1;
  int tbclr : 1;
  int _unused0 : 1;
  int mc : 2;
  int id : 2;
  int tbssel : 2;
  int _unused1 : 1;
  int cntl : 2;
  int tbclgrp : 2;
  int _unused2 : 1;
} msp430_timer_b_control_t;
# 35 "TraceEntry.h"
nx_struct CallTraceEntry {
  nx_uint8_t moduleId;
  nx_uint8_t functionId;
} __attribute__((packed)) __attribute((packed)) ;
typedef nx_struct CallTraceEntry CallTraceEntry;


nx_struct RadioTraceEntry {
  nx_uint8_t address;
  nx_uint16_t magic;
} __attribute__((packed)) __attribute((packed)) ;
typedef nx_struct RadioTraceEntry RadioTraceEntry;


nx_struct TraceEntry {
  nx_uint8_t type;
  nx_union __nesc_unnamed4265 {
    CallTraceEntry callTrace;
    RadioTraceEntry radioTrace;
  } __attribute__((packed)) u;
} __attribute__((packed)) __attribute((packed)) ;
typedef nx_struct TraceEntry TraceEntry;


enum __nesc_unnamed4266 {
  AM_TRACEENTRY = 28
};
# 37 "TraceRecorder.h"
enum __nesc_unnamed4267 {
  ENTRIES_PER_LOG = 50, 
  NUM_BUFFERS = 2
};


enum __nesc_unnamed4268 {
  AM_TRACEMESSAGE = AM_TRACEENTRY + 1
};


nx_struct TraceMessage {
  nx_bool request;
} __attribute__((packed));
typedef nx_struct TraceMessage TraceMessage;



nx_struct TraceLogEntry {
  TraceEntry entries[ENTRIES_PER_LOG];
} __attribute__((packed));
typedef nx_struct TraceLogEntry TraceLogEntry;
# 30 "/opt/tinyos-2.x/tos/types/Leds.h"
enum __nesc_unnamed4269 {
  LEDS_LED0 = 1 << 0, 
  LEDS_LED1 = 1 << 1, 
  LEDS_LED2 = 1 << 2, 
  LEDS_LED3 = 1 << 3, 
  LEDS_LED4 = 1 << 4, 
  LEDS_LED5 = 1 << 5, 
  LEDS_LED6 = 1 << 6, 
  LEDS_LED7 = 1 << 7
};
# 39 "/opt/tinyos-2.x/tos/chips/cc2420/CC2420.h"
typedef uint8_t cc2420_status_t;
#line 59
#line 45
typedef nx_struct cc2420_header_t {
  nxle_uint8_t length;
  nxle_uint16_t fcf;
  nxle_uint8_t dsn;
  nxle_uint16_t destpan;
  nxle_uint16_t dest;
  nxle_uint16_t src;






  nxle_uint8_t type;
} __attribute__((packed)) cc2420_header_t;





#line 64
typedef nx_struct cc2420_footer_t {
} __attribute__((packed)) cc2420_footer_t;
#line 86
#line 71
typedef nx_struct cc2420_metadata_t {
  nx_uint8_t tx_power;
  nx_uint8_t rssi;
  nx_uint8_t lqi;
  nx_bool crc;
  nx_bool ack;
  nx_uint16_t time;
  nx_uint16_t rxInterval;
} __attribute__((packed)) 






cc2420_metadata_t;





#line 89
typedef nx_struct cc2420_packet_t {
  cc2420_header_t packet;
  nx_uint8_t data[];
} __attribute__((packed)) cc2420_packet_t;
#line 123
enum __nesc_unnamed4270 {

  MAC_HEADER_SIZE = sizeof(cc2420_header_t ) - 1, 

  MAC_FOOTER_SIZE = sizeof(uint16_t ), 

  MAC_PACKET_SIZE = MAC_HEADER_SIZE + 28 + MAC_FOOTER_SIZE
};

enum cc2420_enums {
  CC2420_TIME_ACK_TURNAROUND = 7, 
  CC2420_TIME_VREN = 20, 
  CC2420_TIME_SYMBOL = 2, 
  CC2420_BACKOFF_PERIOD = 20 / CC2420_TIME_SYMBOL, 
  CC2420_MIN_BACKOFF = 20 / CC2420_TIME_SYMBOL, 
  CC2420_ACK_WAIT_DELAY = 256
};

enum cc2420_status_enums {
  CC2420_STATUS_RSSI_VALID = 1 << 1, 
  CC2420_STATUS_LOCK = 1 << 2, 
  CC2420_STATUS_TX_ACTIVE = 1 << 3, 
  CC2420_STATUS_ENC_BUSY = 1 << 4, 
  CC2420_STATUS_TX_UNDERFLOW = 1 << 5, 
  CC2420_STATUS_XOSC16M_STABLE = 1 << 6
};

enum cc2420_config_reg_enums {
  CC2420_SNOP = 0x00, 
  CC2420_SXOSCON = 0x01, 
  CC2420_STXCAL = 0x02, 
  CC2420_SRXON = 0x03, 
  CC2420_STXON = 0x04, 
  CC2420_STXONCCA = 0x05, 
  CC2420_SRFOFF = 0x06, 
  CC2420_SXOSCOFF = 0x07, 
  CC2420_SFLUSHRX = 0x08, 
  CC2420_SFLUSHTX = 0x09, 
  CC2420_SACK = 0x0a, 
  CC2420_SACKPEND = 0x0b, 
  CC2420_SRXDEC = 0x0c, 
  CC2420_STXENC = 0x0d, 
  CC2420_SAES = 0x0e, 
  CC2420_MAIN = 0x10, 
  CC2420_MDMCTRL0 = 0x11, 
  CC2420_MDMCTRL1 = 0x12, 
  CC2420_RSSI = 0x13, 
  CC2420_SYNCWORD = 0x14, 
  CC2420_TXCTRL = 0x15, 
  CC2420_RXCTRL0 = 0x16, 
  CC2420_RXCTRL1 = 0x17, 
  CC2420_FSCTRL = 0x18, 
  CC2420_SECCTRL0 = 0x19, 
  CC2420_SECCTRL1 = 0x1a, 
  CC2420_BATTMON = 0x1b, 
  CC2420_IOCFG0 = 0x1c, 
  CC2420_IOCFG1 = 0x1d, 
  CC2420_MANFIDL = 0x1e, 
  CC2420_MANFIDH = 0x1f, 
  CC2420_FSMTC = 0x20, 
  CC2420_MANAND = 0x21, 
  CC2420_MANOR = 0x22, 
  CC2420_AGCCTRL = 0x23, 
  CC2420_AGCTST0 = 0x24, 
  CC2420_AGCTST1 = 0x25, 
  CC2420_AGCTST2 = 0x26, 
  CC2420_FSTST0 = 0x27, 
  CC2420_FSTST1 = 0x28, 
  CC2420_FSTST2 = 0x29, 
  CC2420_FSTST3 = 0x2a, 
  CC2420_RXBPFTST = 0x2b, 
  CC2420_FMSTATE = 0x2c, 
  CC2420_ADCTST = 0x2d, 
  CC2420_DACTST = 0x2e, 
  CC2420_TOPTST = 0x2f, 
  CC2420_TXFIFO = 0x3e, 
  CC2420_RXFIFO = 0x3f
};

enum cc2420_ram_addr_enums {
  CC2420_RAM_TXFIFO = 0x000, 
  CC2420_RAM_RXFIFO = 0x080, 
  CC2420_RAM_KEY0 = 0x100, 
  CC2420_RAM_RXNONCE = 0x110, 
  CC2420_RAM_SABUF = 0x120, 
  CC2420_RAM_KEY1 = 0x130, 
  CC2420_RAM_TXNONCE = 0x140, 
  CC2420_RAM_CBCSTATE = 0x150, 
  CC2420_RAM_IEEEADR = 0x160, 
  CC2420_RAM_PANID = 0x168, 
  CC2420_RAM_SHORTADR = 0x16a
};

enum cc2420_nonce_enums {
  CC2420_NONCE_BLOCK_COUNTER = 0, 
  CC2420_NONCE_KEY_SEQ_COUNTER = 2, 
  CC2420_NONCE_FRAME_COUNTER = 3, 
  CC2420_NONCE_SOURCE_ADDRESS = 7, 
  CC2420_NONCE_FLAGS = 15
};

enum cc2420_main_enums {
  CC2420_MAIN_RESETn = 15, 
  CC2420_MAIN_ENC_RESETn = 14, 
  CC2420_MAIN_DEMOD_RESETn = 13, 
  CC2420_MAIN_MOD_RESETn = 12, 
  CC2420_MAIN_FS_RESETn = 11, 
  CC2420_MAIN_XOSC16M_BYPASS = 0
};

enum cc2420_mdmctrl0_enums {
  CC2420_MDMCTRL0_RESERVED_FRAME_MODE = 13, 
  CC2420_MDMCTRL0_PAN_COORDINATOR = 12, 
  CC2420_MDMCTRL0_ADR_DECODE = 11, 
  CC2420_MDMCTRL0_CCA_HYST = 8, 
  CC2420_MDMCTRL0_CCA_MOD = 6, 
  CC2420_MDMCTRL0_AUTOCRC = 5, 
  CC2420_MDMCTRL0_AUTOACK = 4, 
  CC2420_MDMCTRL0_PREAMBLE_LENGTH = 0
};

enum cc2420_mdmctrl1_enums {
  CC2420_MDMCTRL1_CORR_THR = 6, 
  CC2420_MDMCTRL1_DEMOD_AVG_MODE = 5, 
  CC2420_MDMCTRL1_MODULATION_MODE = 4, 
  CC2420_MDMCTRL1_TX_MODE = 2, 
  CC2420_MDMCTRL1_RX_MODE = 0
};

enum cc2420_rssi_enums {
  CC2420_RSSI_CCA_THR = 8, 
  CC2420_RSSI_RSSI_VAL = 0
};

enum cc2420_syncword_enums {
  CC2420_SYNCWORD_SYNCWORD = 0
};

enum cc2420_txctrl_enums {
  CC2420_TXCTRL_TXMIXBUF_CUR = 14, 
  CC2420_TXCTRL_TX_TURNAROUND = 13, 
  CC2420_TXCTRL_TXMIX_CAP_ARRAY = 11, 
  CC2420_TXCTRL_TXMIX_CURRENT = 9, 
  CC2420_TXCTRL_PA_CURRENT = 6, 
  CC2420_TXCTRL_RESERVED = 5, 
  CC2420_TXCTRL_PA_LEVEL = 0
};

enum cc2420_rxctrl0_enums {
  CC2420_RXCTRL0_RXMIXBUF_CUR = 12, 
  CC2420_RXCTRL0_HIGH_LNA_GAIN = 10, 
  CC2420_RXCTRL0_MED_LNA_GAIN = 8, 
  CC2420_RXCTRL0_LOW_LNA_GAIN = 6, 
  CC2420_RXCTRL0_HIGH_LNA_CURRENT = 4, 
  CC2420_RXCTRL0_MED_LNA_CURRENT = 2, 
  CC2420_RXCTRL0_LOW_LNA_CURRENT = 0
};

enum cc2420_rxctrl1_enums {
  CC2420_RXCTRL1_RXBPF_LOCUR = 13, 
  CC2420_RXCTRL1_RXBPF_MIDCUR = 12, 
  CC2420_RXCTRL1_LOW_LOWGAIN = 11, 
  CC2420_RXCTRL1_MED_LOWGAIN = 10, 
  CC2420_RXCTRL1_HIGH_HGM = 9, 
  CC2420_RXCTRL1_MED_HGM = 8, 
  CC2420_RXCTRL1_LNA_CAP_ARRAY = 6, 
  CC2420_RXCTRL1_RXMIX_TAIL = 4, 
  CC2420_RXCTRL1_RXMIX_VCM = 2, 
  CC2420_RXCTRL1_RXMIX_CURRENT = 0
};

enum cc2420_rsctrl_enums {
  CC2420_FSCTRL_LOCK_THR = 14, 
  CC2420_FSCTRL_CAL_DONE = 13, 
  CC2420_FSCTRL_CAL_RUNNING = 12, 
  CC2420_FSCTRL_LOCK_LENGTH = 11, 
  CC2420_FSCTRL_LOCK_STATUS = 10, 
  CC2420_FSCTRL_FREQ = 0
};

enum cc2420_secctrl0_enums {
  CC2420_SECCTRL0_RXFIFO_PROTECTION = 9, 
  CC2420_SECCTRL0_SEC_CBC_HEAD = 8, 
  CC2420_SECCTRL0_SEC_SAKEYSEL = 7, 
  CC2420_SECCTRL0_SEC_TXKEYSEL = 6, 
  CC2420_SECCTRL0_SEC_RXKEYSEL = 5, 
  CC2420_SECCTRL0_SEC_M = 2, 
  CC2420_SECCTRL0_SEC_MODE = 0
};

enum cc2420_secctrl1_enums {
  CC2420_SECCTRL1_SEC_TXL = 8, 
  CC2420_SECCTRL1_SEC_RXL = 0
};

enum cc2420_battmon_enums {
  CC2420_BATTMON_BATT_OK = 6, 
  CC2420_BATTMON_BATTMON_EN = 5, 
  CC2420_BATTMON_BATTMON_VOLTAGE = 0
};

enum cc2420_iocfg0_enums {
  CC2420_IOCFG0_BCN_ACCEPT = 11, 
  CC2420_IOCFG0_FIFO_POLARITY = 10, 
  CC2420_IOCFG0_FIFOP_POLARITY = 9, 
  CC2420_IOCFG0_SFD_POLARITY = 8, 
  CC2420_IOCFG0_CCA_POLARITY = 7, 
  CC2420_IOCFG0_FIFOP_THR = 0
};

enum cc2420_iocfg1_enums {
  CC2420_IOCFG1_HSSD_SRC = 10, 
  CC2420_IOCFG1_SFDMUX = 5, 
  CC2420_IOCFG1_CCAMUX = 0
};

enum cc2420_manfidl_enums {
  CC2420_MANFIDL_PARTNUM = 12, 
  CC2420_MANFIDL_MANFID = 0
};

enum cc2420_manfidh_enums {
  CC2420_MANFIDH_VERSION = 12, 
  CC2420_MANFIDH_PARTNUM = 0
};

enum cc2420_fsmtc_enums {
  CC2420_FSMTC_TC_RXCHAIN2RX = 13, 
  CC2420_FSMTC_TC_SWITCH2TX = 10, 
  CC2420_FSMTC_TC_PAON2TX = 6, 
  CC2420_FSMTC_TC_TXEND2SWITCH = 3, 
  CC2420_FSMTC_TC_TXEND2PAOFF = 0
};

enum cc2420_sfdmux_enums {
  CC2420_SFDMUX_SFD = 0, 
  CC2420_SFDMUX_XOSC16M_STABLE = 24
};
# 6 "/opt/tinyos-2.x/tos/types/AM.h"
typedef nx_uint8_t nx_am_id_t;
typedef nx_uint8_t nx_am_group_t;
typedef nx_uint16_t nx_am_addr_t;

typedef uint8_t am_id_t;
typedef uint8_t am_group_t;
typedef uint16_t am_addr_t;

enum __nesc_unnamed4271 {
  AM_BROADCAST_ADDR = 0xffff
};









enum __nesc_unnamed4272 {
  TOS_AM_GROUP = 0x22, 
  TOS_AM_ADDRESS = 1
};
# 72 "/opt/tinyos-2.x/tos/lib/serial/Serial.h"
typedef uint8_t uart_id_t;



enum __nesc_unnamed4273 {
  HDLC_FLAG_BYTE = 0x7e, 
  HDLC_CTLESC_BYTE = 0x7d
};



enum __nesc_unnamed4274 {
  TOS_SERIAL_ACTIVE_MESSAGE_ID = 0, 
  TOS_SERIAL_CC1000_ID = 1, 
  TOS_SERIAL_802_15_4_ID = 2, 
  TOS_SERIAL_UNKNOWN_ID = 255
};


enum __nesc_unnamed4275 {
  SERIAL_PROTO_ACK = 67, 
  SERIAL_PROTO_PACKET_ACK = 68, 
  SERIAL_PROTO_PACKET_NOACK = 69, 
  SERIAL_PROTO_PACKET_UNKNOWN = 255
};
#line 110
#line 98
typedef struct radio_stats {
  uint8_t version;
  uint8_t flags;
  uint8_t reserved;
  uint8_t platform;
  uint16_t MTU;
  uint16_t radio_crc_fail;
  uint16_t radio_queue_drops;
  uint16_t serial_crc_fail;
  uint16_t serial_tx_fail;
  uint16_t serial_short_packets;
  uint16_t serial_proto_drops;
} radio_stats_t;







#line 112
typedef nx_struct serial_header {
  nx_am_addr_t dest;
  nx_am_addr_t src;
  nx_uint8_t length;
  nx_am_group_t group;
  nx_am_id_t type;
} __attribute__((packed)) serial_header_t;




#line 120
typedef nx_struct serial_packet {
  serial_header_t header;
  nx_uint8_t data[];
} __attribute__((packed)) serial_packet_t;
# 48 "/opt/tinyos-2.x/tos/platforms/telosa/platform_message.h"
#line 45
typedef union message_header {
  cc2420_header_t cc2420;
  serial_header_t serial;
} message_header_t;



#line 50
typedef union TOSRadioFooter {
  cc2420_footer_t cc2420;
} message_footer_t;



#line 54
typedef union TOSRadioMetadata {
  cc2420_metadata_t cc2420;
} message_metadata_t;
# 19 "/opt/tinyos-2.x/tos/types/message.h"
#line 14
typedef nx_struct message_t {
  nx_uint8_t header[sizeof(message_header_t )];
  nx_uint8_t data[28];
  nx_uint8_t footer[sizeof(message_footer_t )];
  nx_uint8_t metadata[sizeof(message_metadata_t )];
} __attribute__((packed)) message_t;
# 33 "/opt/tinyos-2.x/tos/types/Resource.h"
typedef uint8_t resource_client_id_t;
# 29 "/opt/tinyos-2.x/tos/lib/timer/Timer.h"
typedef struct __nesc_unnamed4276 {
#line 29
  int notUsed;
} 
#line 29
TMilli;
typedef struct __nesc_unnamed4277 {
#line 30
  int notUsed;
} 
#line 30
T32khz;
typedef struct __nesc_unnamed4278 {
#line 31
  int notUsed;
} 
#line 31
TMicro;
# 80 "/opt/tinyos-2.x/tos/system/crc.h"
static uint16_t crcByte(uint16_t crc, uint8_t b);
# 56 "/opt/tinyos-2.x/tos/chips/msp430/usart/msp430usart.h"
#line 48
typedef enum __nesc_unnamed4279 {

  USART_NONE = 0, 
  USART_UART = 1, 
  USART_UART_TX = 2, 
  USART_UART_RX = 3, 
  USART_SPI = 4, 
  USART_I2C = 5
} msp430_usartmode_t;










#line 58
typedef struct __nesc_unnamed4280 {
  unsigned int swrst : 1;
  unsigned int mm : 1;
  unsigned int sync : 1;
  unsigned int listen : 1;
  unsigned int clen : 1;
  unsigned int spb : 1;
  unsigned int pev : 1;
  unsigned int pena : 1;
} __attribute((packed))  msp430_uctl_t;









#line 69
typedef struct __nesc_unnamed4281 {
  unsigned int txept : 1;
  unsigned int stc : 1;
  unsigned int txwake : 1;
  unsigned int urxse : 1;
  unsigned int ssel : 2;
  unsigned int ckpl : 1;
  unsigned int ckph : 1;
} __attribute((packed))  msp430_utctl_t;










#line 79
typedef struct __nesc_unnamed4282 {
  unsigned int rxerr : 1;
  unsigned int rxwake : 1;
  unsigned int urxwie : 1;
  unsigned int urxeie : 1;
  unsigned int brk : 1;
  unsigned int oe : 1;
  unsigned int pe : 1;
  unsigned int fe : 1;
} __attribute((packed))  msp430_urctl_t;
#line 116
#line 99
typedef struct __nesc_unnamed4283 {
  unsigned int ubr : 16;

  unsigned int  : 1;
  unsigned int mm : 1;
  unsigned int  : 1;
  unsigned int listen : 1;
  unsigned int clen : 1;
  unsigned int  : 3;

  unsigned int  : 1;
  unsigned int stc : 1;
  unsigned int  : 2;
  unsigned int ssel : 2;
  unsigned int ckpl : 1;
  unsigned int ckph : 1;
  unsigned int  : 0;
} msp430_spi_config_t;





#line 118
typedef struct __nesc_unnamed4284 {
  uint16_t ubr;
  uint8_t uctl;
  uint8_t utctl;
} msp430_spi_registers_t;




#line 124
typedef union __nesc_unnamed4285 {
  msp430_spi_config_t spiConfig;
  msp430_spi_registers_t spiRegisters;
} msp430_spi_union_config_t;

msp430_spi_union_config_t msp430_spi_default_config = { 
{ 
.ubr = 0x0002, 
.ssel = 0x02, 
.clen = 1, 
.listen = 0, 
.mm = 1, 
.ckph = 1, 
.ckpl = 0, 
.stc = 1 } };
#line 169
#line 150
typedef enum __nesc_unnamed4286 {

  UBR_32KHZ_1200 = 0x001B, UMCTL_32KHZ_1200 = 0x94, 
  UBR_32KHZ_1800 = 0x0012, UMCTL_32KHZ_1800 = 0x84, 
  UBR_32KHZ_2400 = 0x000D, UMCTL_32KHZ_2400 = 0x6D, 
  UBR_32KHZ_4800 = 0x0006, UMCTL_32KHZ_4800 = 0x77, 
  UBR_32KHZ_9600 = 0x0003, UMCTL_32KHZ_9600 = 0x29, 

  UBR_1MHZ_1200 = 0x0369, UMCTL_1MHZ_1200 = 0x7B, 
  UBR_1MHZ_1800 = 0x0246, UMCTL_1MHZ_1800 = 0x55, 
  UBR_1MHZ_2400 = 0x01B4, UMCTL_1MHZ_2400 = 0xDF, 
  UBR_1MHZ_4800 = 0x00DA, UMCTL_1MHZ_4800 = 0xAA, 
  UBR_1MHZ_9600 = 0x006D, UMCTL_1MHZ_9600 = 0x44, 
  UBR_1MHZ_19200 = 0x0036, UMCTL_1MHZ_19200 = 0xB5, 
  UBR_1MHZ_38400 = 0x001B, UMCTL_1MHZ_38400 = 0x94, 
  UBR_1MHZ_57600 = 0x0012, UMCTL_1MHZ_57600 = 0x84, 
  UBR_1MHZ_76800 = 0x000D, UMCTL_1MHZ_76800 = 0x6D, 
  UBR_1MHZ_115200 = 0x0009, UMCTL_1MHZ_115200 = 0x10, 
  UBR_1MHZ_230400 = 0x0004, UMCTL_1MHZ_230400 = 0x55
} msp430_uart_rate_t;
#line 200
#line 171
typedef struct __nesc_unnamed4287 {
  unsigned int ubr : 16;

  unsigned int umctl : 8;

  unsigned int  : 1;
  unsigned int mm : 1;
  unsigned int  : 1;
  unsigned int listen : 1;
  unsigned int clen : 1;
  unsigned int spb : 1;
  unsigned int pev : 1;
  unsigned int pena : 1;
  unsigned int  : 0;

  unsigned int  : 3;
  unsigned int urxse : 1;
  unsigned int ssel : 2;
  unsigned int ckpl : 1;
  unsigned int  : 1;

  unsigned int  : 2;
  unsigned int urxwie : 1;
  unsigned int urxeie : 1;
  unsigned int  : 4;
  unsigned int  : 0;

  unsigned int utxe : 1;
  unsigned int urxe : 1;
} msp430_uart_config_t;








#line 202
typedef struct __nesc_unnamed4288 {
  uint16_t ubr;
  uint8_t umctl;
  uint8_t uctl;
  uint8_t utctl;
  uint8_t urctl;
  uint8_t ume;
} msp430_uart_registers_t;




#line 211
typedef union __nesc_unnamed4289 {
  msp430_uart_config_t uartConfig;
  msp430_uart_registers_t uartRegisters;
} msp430_uart_union_config_t;

msp430_uart_union_config_t msp430_uart_default_config = { 
{ 
.utxe = 1, 
.urxe = 1, 
.ubr = UBR_1MHZ_57600, 
.umctl = UMCTL_1MHZ_57600, 
.ssel = 0x02, 
.pena = 0, 
.pev = 0, 
.spb = 0, 
.clen = 1, 
.listen = 0, 
.mm = 0, 
.ckpl = 0, 
.urxse = 0, 
.urxeie = 1, 
.urxwie = 0, 
.utxe = 1, 
.urxe = 1 } };
#line 248
#line 240
typedef struct __nesc_unnamed4290 {
  unsigned int i2cstt : 1;
  unsigned int i2cstp : 1;
  unsigned int i2cstb : 1;
  unsigned int i2cctrx : 1;
  unsigned int i2cssel : 2;
  unsigned int i2ccrm : 1;
  unsigned int i2cword : 1;
} __attribute((packed))  msp430_i2ctctl_t;
#line 276
#line 253
typedef struct __nesc_unnamed4291 {
  unsigned int  : 1;
  unsigned int mst : 1;
  unsigned int  : 1;
  unsigned int listen : 1;
  unsigned int xa : 1;
  unsigned int  : 1;
  unsigned int txdmaen : 1;
  unsigned int rxdmaen : 1;

  unsigned int  : 4;
  unsigned int i2cssel : 2;
  unsigned int i2crm : 1;
  unsigned int i2cword : 1;

  unsigned int i2cpsc : 8;

  unsigned int i2csclh : 8;

  unsigned int i2cscll : 8;

  unsigned int i2coa : 10;
  unsigned int  : 6;
} msp430_i2c_config_t;








#line 278
typedef struct __nesc_unnamed4292 {
  uint8_t uctl;
  uint8_t i2ctctl;
  uint8_t i2cpsc;
  uint8_t i2csclh;
  uint8_t i2cscll;
  uint16_t i2coa;
} msp430_i2c_registers_t;




#line 287
typedef union __nesc_unnamed4293 {
  msp430_i2c_config_t i2cConfig;
  msp430_i2c_registers_t i2cRegisters;
} msp430_i2c_union_config_t;
enum /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$__nesc_unnamed4294 {
  LogStorageC$0$LOG_ID = 0U, LogStorageC$0$VOLUME_ID = 0U
};
typedef bool Stm25pLogP$Circular$val_t;
enum /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Timer*/Msp430Timer32khzC$0$__nesc_unnamed4295 {
  Msp430Timer32khzC$0$ALARM_ID = 0U
};
typedef T32khz /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$frequency_tag;
typedef /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$frequency_tag /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Alarm$precision_tag;
typedef uint16_t /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Alarm$size_type;
typedef T32khz /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$frequency_tag;
typedef /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$frequency_tag /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Counter$precision_tag;
typedef uint16_t /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Counter$size_type;
typedef TMilli /*CounterMilli32C.Transform*/TransformCounterC$0$to_precision_tag;
typedef uint32_t /*CounterMilli32C.Transform*/TransformCounterC$0$to_size_type;
typedef T32khz /*CounterMilli32C.Transform*/TransformCounterC$0$from_precision_tag;
typedef uint16_t /*CounterMilli32C.Transform*/TransformCounterC$0$from_size_type;
typedef uint32_t /*CounterMilli32C.Transform*/TransformCounterC$0$upper_count_type;
typedef /*CounterMilli32C.Transform*/TransformCounterC$0$from_precision_tag /*CounterMilli32C.Transform*/TransformCounterC$0$CounterFrom$precision_tag;
typedef /*CounterMilli32C.Transform*/TransformCounterC$0$from_size_type /*CounterMilli32C.Transform*/TransformCounterC$0$CounterFrom$size_type;
typedef /*CounterMilli32C.Transform*/TransformCounterC$0$to_precision_tag /*CounterMilli32C.Transform*/TransformCounterC$0$Counter$precision_tag;
typedef /*CounterMilli32C.Transform*/TransformCounterC$0$to_size_type /*CounterMilli32C.Transform*/TransformCounterC$0$Counter$size_type;
typedef TMilli /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_precision_tag;
typedef uint32_t /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type;
typedef T32khz /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$from_precision_tag;
typedef uint16_t /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$from_size_type;
typedef /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_precision_tag /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$precision_tag;
typedef /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$size_type;
typedef /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$from_precision_tag /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$AlarmFrom$precision_tag;
typedef /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$from_size_type /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$AlarmFrom$size_type;
typedef /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_precision_tag /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Counter$precision_tag;
typedef /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Counter$size_type;
typedef TMilli /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$precision_tag;
typedef /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$precision_tag /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$precision_tag;
typedef uint32_t /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$size_type;
typedef /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$precision_tag /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Timer$precision_tag;
typedef TMilli /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$precision_tag;
typedef /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$precision_tag /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$TimerFrom$precision_tag;
typedef /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$precision_tag /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer$precision_tag;
typedef TMilli /*HilTimerMilliC.CounterToLocalTimeC*/CounterToLocalTimeC$0$precision_tag;
typedef /*HilTimerMilliC.CounterToLocalTimeC*/CounterToLocalTimeC$0$precision_tag /*HilTimerMilliC.CounterToLocalTimeC*/CounterToLocalTimeC$0$LocalTime$precision_tag;
typedef /*HilTimerMilliC.CounterToLocalTimeC*/CounterToLocalTimeC$0$precision_tag /*HilTimerMilliC.CounterToLocalTimeC*/CounterToLocalTimeC$0$Counter$precision_tag;
typedef uint32_t /*HilTimerMilliC.CounterToLocalTimeC*/CounterToLocalTimeC$0$Counter$size_type;
typedef TMilli /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$TimerMilli$precision_tag;
enum /*HplStm25pSpiC.SpiC*/Msp430Spi0C$0$__nesc_unnamed4296 {
  Msp430Spi0C$0$CLIENT_ID = 0U
};
enum /*HplStm25pSpiC.SpiC.UsartC*/Msp430Usart0C$0$__nesc_unnamed4297 {
  Msp430Usart0C$0$CLIENT_ID = 0U
};
typedef bool /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC.ConfigP*/Stm25pLogConfigP$0$Circular$val_t;
enum /*PlatformSerialC.UartC*/Msp430Uart1C$0$__nesc_unnamed4298 {
  Msp430Uart1C$0$CLIENT_ID = 0U
};
typedef T32khz /*Msp430Uart1P.UartP*/Msp430UartP$0$Counter$precision_tag;
typedef uint16_t /*Msp430Uart1P.UartP*/Msp430UartP$0$Counter$size_type;
enum /*PlatformSerialC.UartC.UsartC*/Msp430Usart1C$0$__nesc_unnamed4299 {
  Msp430Usart1C$0$CLIENT_ID = 0U
};
enum SerialAMQueueP$__nesc_unnamed4300 {
  SerialAMQueueP$NUM_CLIENTS = 2U
};
# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
static  error_t PlatformP$Init$init(void);
#line 51
static  error_t MotePlatformC$Init$init(void);
# 35 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430ClockInit.nc"
static  void Msp430ClockP$Msp430ClockInit$defaultInitClocks(void);
#line 32
static  void Msp430ClockP$Msp430ClockInit$default$initTimerB(void);



static  void Msp430ClockP$Msp430ClockInit$defaultInitTimerA(void);
#line 31
static  void Msp430ClockP$Msp430ClockInit$default$initTimerA(void);





static  void Msp430ClockP$Msp430ClockInit$defaultInitTimerB(void);
#line 34
static  void Msp430ClockP$Msp430ClockInit$defaultSetupDcoCalibrate(void);
#line 29
static  void Msp430ClockP$Msp430ClockInit$default$setupDcoCalibrate(void);
static  void Msp430ClockP$Msp430ClockInit$default$initClocks(void);
# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
static  error_t Msp430ClockP$Init$init(void);
# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
static   void /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$VectorTimerX0$fired(void);
#line 28
static   void /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$Overflow$fired(void);
#line 28
static   void /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$VectorTimerX1$fired(void);
#line 28
static   void /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$Event$default$fired(
# 40 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerP.nc"
uint8_t arg_0x4056f4f0);
# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
static   void /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$VectorTimerX0$fired(void);
#line 28
static   void /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Overflow$fired(void);
#line 28
static   void /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$VectorTimerX1$fired(void);
#line 28
static   void /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Event$default$fired(
# 40 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerP.nc"
uint8_t arg_0x4056f4f0);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   uint16_t /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Timer$get(void);
static   bool /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Timer$isOverflowPending(void);
# 33 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   uint16_t /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Capture$getEvent(void);
#line 75
static   void /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Capture$default$captured(uint16_t arg_0x40557100);
# 31 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerControl.nc"
static   msp430_compare_control_t /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Control$getControl(void);
# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
static   void /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Event$fired(void);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Compare$default$fired(void);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   void /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Timer$overflow(void);
# 33 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   uint16_t /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Capture$getEvent(void);
#line 75
static   void /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Capture$default$captured(uint16_t arg_0x40557100);
# 31 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerControl.nc"
static   msp430_compare_control_t /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Control$getControl(void);
# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
static   void /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Event$fired(void);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Compare$default$fired(void);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   void /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Timer$overflow(void);
# 33 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   uint16_t /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Capture$getEvent(void);
#line 75
static   void /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Capture$default$captured(uint16_t arg_0x40557100);
# 31 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerControl.nc"
static   msp430_compare_control_t /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Control$getControl(void);
# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
static   void /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Event$fired(void);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Compare$default$fired(void);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   void /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Timer$overflow(void);
# 33 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   uint16_t /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Capture$getEvent(void);
#line 75
static   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Capture$default$captured(uint16_t arg_0x40557100);
# 31 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerControl.nc"
static   msp430_compare_control_t /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$getControl(void);







static   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$enableEvents(void);
#line 36
static   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$setControlAsCompare(void);



static   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$disableEvents(void);
#line 33
static   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$clearPendingInterrupt(void);
# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
static   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Event$fired(void);
# 30 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Compare$setEvent(uint16_t arg_0x4054f030);

static   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Compare$setEventFromNow(uint16_t arg_0x4054f978);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Timer$overflow(void);
# 33 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   uint16_t /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Capture$getEvent(void);
#line 75
static   void /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Capture$default$captured(uint16_t arg_0x40557100);
# 31 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerControl.nc"
static   msp430_compare_control_t /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Control$getControl(void);
# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
static   void /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Event$fired(void);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Compare$default$fired(void);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   void /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Timer$overflow(void);
# 33 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   uint16_t /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Capture$getEvent(void);
#line 75
static   void /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Capture$default$captured(uint16_t arg_0x40557100);
# 31 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerControl.nc"
static   msp430_compare_control_t /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Control$getControl(void);
# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
static   void /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Event$fired(void);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Compare$default$fired(void);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   void /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Timer$overflow(void);
# 33 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   uint16_t /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Capture$getEvent(void);
#line 75
static   void /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Capture$default$captured(uint16_t arg_0x40557100);
# 31 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerControl.nc"
static   msp430_compare_control_t /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Control$getControl(void);
# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
static   void /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Event$fired(void);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Compare$default$fired(void);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   void /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Timer$overflow(void);
# 33 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   uint16_t /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Capture$getEvent(void);
#line 75
static   void /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Capture$default$captured(uint16_t arg_0x40557100);
# 31 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerControl.nc"
static   msp430_compare_control_t /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Control$getControl(void);
# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
static   void /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Event$fired(void);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Compare$default$fired(void);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   void /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Timer$overflow(void);
# 33 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   uint16_t /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Capture$getEvent(void);
#line 75
static   void /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Capture$default$captured(uint16_t arg_0x40557100);
# 31 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerControl.nc"
static   msp430_compare_control_t /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Control$getControl(void);
# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
static   void /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Event$fired(void);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Compare$default$fired(void);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   void /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Timer$overflow(void);
# 33 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   uint16_t /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Capture$getEvent(void);
#line 75
static   void /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Capture$default$captured(uint16_t arg_0x40557100);
# 31 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerControl.nc"
static   msp430_compare_control_t /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Control$getControl(void);
# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
static   void /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Event$fired(void);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Compare$default$fired(void);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   void /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Timer$overflow(void);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t SchedulerBasicP$TaskBasic$postTask(
# 45 "/opt/tinyos-2.x/tos/system/SchedulerBasicP.nc"
uint8_t arg_0x404aa1e0);
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void SchedulerBasicP$TaskBasic$default$runTask(
# 45 "/opt/tinyos-2.x/tos/system/SchedulerBasicP.nc"
uint8_t arg_0x404aa1e0);
# 46 "/opt/tinyos-2.x/tos/interfaces/Scheduler.nc"
static  void SchedulerBasicP$Scheduler$init(void);
#line 61
static  void SchedulerBasicP$Scheduler$taskLoop(void);
#line 54
static  bool SchedulerBasicP$Scheduler$runNextTask(void);
# 54 "/opt/tinyos-2.x/tos/interfaces/McuPowerOverride.nc"
static   mcu_power_t McuSleepC$McuPowerOverride$default$lowestState(void);
# 59 "/opt/tinyos-2.x/tos/interfaces/McuSleep.nc"
static   void McuSleepC$McuSleep$sleep(void);
# 67 "/opt/tinyos-2.x/tos/interfaces/Receive.nc"
static  message_t *TraceRetrieverP$UARTReceive$receive(message_t *arg_0x406437d0, void *arg_0x40643968, uint8_t arg_0x40643ae8);
# 92 "/opt/tinyos-2.x/tos/interfaces/SplitControl.nc"
static  void TraceRetrieverP$AMControl$startDone(error_t arg_0x40620638);
#line 117
static  void TraceRetrieverP$AMControl$stopDone(error_t arg_0x4061f1e0);
# 49 "/opt/tinyos-2.x/tos/interfaces/Boot.nc"
static  void TraceRetrieverP$Boot$booted(void);
# 99 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
static  void TraceRetrieverP$UARTSendData$sendDone(message_t *arg_0x40636ea0, error_t arg_0x40635030);
# 75 "/opt/tinyos-2.x/tos/interfaces/LogRead.nc"
static  void TraceRetrieverP$LogRead$readDone(void *arg_0x40613668, storage_len_t arg_0x406137f0, error_t arg_0x40613970);
#line 115
static  void TraceRetrieverP$LogRead$seekDone(error_t arg_0x40612d00);
# 99 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
static  void TraceRetrieverP$UARTSendAck$sendDone(message_t *arg_0x40636ea0, error_t arg_0x40635030);
# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
static  error_t LedsP$Init$init(void);
# 72 "/opt/tinyos-2.x/tos/interfaces/Leds.nc"
static   void LedsP$Leds$led1Toggle(void);
#line 45
static   void LedsP$Leds$led0On(void);
# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
static   void /*HplMsp430GeneralIOC.P31*/HplMsp430GeneralIOP$17$IO$selectIOFunc(void);
#line 78
static   void /*HplMsp430GeneralIOC.P31*/HplMsp430GeneralIOP$17$IO$selectModuleFunc(void);






static   void /*HplMsp430GeneralIOC.P32*/HplMsp430GeneralIOP$18$IO$selectIOFunc(void);
#line 78
static   void /*HplMsp430GeneralIOC.P32*/HplMsp430GeneralIOP$18$IO$selectModuleFunc(void);






static   void /*HplMsp430GeneralIOC.P33*/HplMsp430GeneralIOP$19$IO$selectIOFunc(void);
#line 78
static   void /*HplMsp430GeneralIOC.P33*/HplMsp430GeneralIOP$19$IO$selectModuleFunc(void);






static   void /*HplMsp430GeneralIOC.P34*/HplMsp430GeneralIOP$20$IO$selectIOFunc(void);
#line 85
static   void /*HplMsp430GeneralIOC.P35*/HplMsp430GeneralIOP$21$IO$selectIOFunc(void);
#line 85
static   void /*HplMsp430GeneralIOC.P36*/HplMsp430GeneralIOP$22$IO$selectIOFunc(void);
#line 78
static   void /*HplMsp430GeneralIOC.P36*/HplMsp430GeneralIOP$22$IO$selectModuleFunc(void);






static   void /*HplMsp430GeneralIOC.P37*/HplMsp430GeneralIOP$23$IO$selectIOFunc(void);
#line 78
static   void /*HplMsp430GeneralIOC.P37*/HplMsp430GeneralIOP$23$IO$selectModuleFunc(void);
#line 71
static   void /*HplMsp430GeneralIOC.P44*/HplMsp430GeneralIOP$28$IO$makeOutput(void);
#line 34
static   void /*HplMsp430GeneralIOC.P44*/HplMsp430GeneralIOP$28$IO$set(void);




static   void /*HplMsp430GeneralIOC.P44*/HplMsp430GeneralIOP$28$IO$clr(void);
#line 71
static   void /*HplMsp430GeneralIOC.P47*/HplMsp430GeneralIOP$31$IO$makeOutput(void);
#line 34
static   void /*HplMsp430GeneralIOC.P47*/HplMsp430GeneralIOP$31$IO$set(void);
#line 85
static   void /*HplMsp430GeneralIOC.P51*/HplMsp430GeneralIOP$33$IO$selectIOFunc(void);
#line 85
static   void /*HplMsp430GeneralIOC.P52*/HplMsp430GeneralIOP$34$IO$selectIOFunc(void);
#line 85
static   void /*HplMsp430GeneralIOC.P53*/HplMsp430GeneralIOP$35$IO$selectIOFunc(void);
#line 71
static   void /*HplMsp430GeneralIOC.P54*/HplMsp430GeneralIOP$36$IO$makeOutput(void);
#line 34
static   void /*HplMsp430GeneralIOC.P54*/HplMsp430GeneralIOP$36$IO$set(void);




static   void /*HplMsp430GeneralIOC.P54*/HplMsp430GeneralIOP$36$IO$clr(void);




static   void /*HplMsp430GeneralIOC.P55*/HplMsp430GeneralIOP$37$IO$toggle(void);
#line 71
static   void /*HplMsp430GeneralIOC.P55*/HplMsp430GeneralIOP$37$IO$makeOutput(void);
#line 34
static   void /*HplMsp430GeneralIOC.P55*/HplMsp430GeneralIOP$37$IO$set(void);
#line 71
static   void /*HplMsp430GeneralIOC.P56*/HplMsp430GeneralIOP$38$IO$makeOutput(void);
#line 34
static   void /*HplMsp430GeneralIOC.P56*/HplMsp430GeneralIOP$38$IO$set(void);
# 35 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
static   void /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$GeneralIO$makeOutput(void);
#line 29
static   void /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$GeneralIO$set(void);
static   void /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$GeneralIO$clr(void);
static   void /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$GeneralIO$toggle(void);



static   void /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$GeneralIO$makeOutput(void);
#line 29
static   void /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$GeneralIO$set(void);





static   void /*PlatformLedsC.Led2Impl*/Msp430GpioC$2$GeneralIO$makeOutput(void);
#line 29
static   void /*PlatformLedsC.Led2Impl*/Msp430GpioC$2$GeneralIO$set(void);
# 68 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
static  error_t Stm25pLogP$Sector$default$read(
# 45 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a0010, 
# 68 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
stm25p_addr_t arg_0x4079a330, uint8_t *arg_0x4079a4d0, stm25p_len_t arg_0x4079a658);
#line 101
static  void Stm25pLogP$Sector$writeDone(
# 45 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a0010, 
# 101 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
stm25p_addr_t arg_0x407b1088, uint8_t *arg_0x407b1228, stm25p_len_t arg_0x407b13b0, 
error_t arg_0x407b1540);









static  error_t Stm25pLogP$Sector$default$erase(
# 45 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a0010, 
# 112 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
uint8_t arg_0x407b1af8, uint8_t arg_0x407b1c80);








static  void Stm25pLogP$Sector$eraseDone(
# 45 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a0010, 
# 121 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
uint8_t arg_0x407b0250, uint8_t arg_0x407b03d8, error_t arg_0x407b0558);
#line 144
static  void Stm25pLogP$Sector$computeCrcDone(
# 45 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a0010, 
# 144 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
stm25p_addr_t arg_0x407af4e8, stm25p_len_t arg_0x407af670, 
uint16_t arg_0x407af808, error_t arg_0x407af988);
#line 91
static  error_t Stm25pLogP$Sector$default$write(
# 45 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a0010, 
# 91 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
stm25p_addr_t arg_0x407b3768, uint8_t *arg_0x407b3908, stm25p_len_t arg_0x407b3a90);
#line 56
static  uint8_t Stm25pLogP$Sector$default$getNumSectors(
# 45 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a0010);
# 78 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
static  void Stm25pLogP$Sector$readDone(
# 45 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a0010, 
# 78 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
stm25p_addr_t arg_0x4079ac48, uint8_t *arg_0x4079ade8, stm25p_len_t arg_0x407b3010, 
error_t arg_0x407b31a0);
# 75 "/opt/tinyos-2.x/tos/interfaces/LogRead.nc"
static  void Stm25pLogP$Read$default$readDone(
# 42 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a3868, 
# 75 "/opt/tinyos-2.x/tos/interfaces/LogRead.nc"
void *arg_0x40613668, storage_len_t arg_0x406137f0, error_t arg_0x40613970);
#line 63
static  error_t Stm25pLogP$Read$read(
# 42 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a3868, 
# 63 "/opt/tinyos-2.x/tos/interfaces/LogRead.nc"
void *arg_0x40614e10, storage_len_t arg_0x40613010);
#line 115
static  void Stm25pLogP$Read$default$seekDone(
# 42 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a3868, 
# 115 "/opt/tinyos-2.x/tos/interfaces/LogRead.nc"
error_t arg_0x40612d00);
# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
static  error_t Stm25pLogP$Init$init(void);
# 118 "/opt/tinyos-2.x/tos/interfaces/LogWrite.nc"
static  void Stm25pLogP$Write$default$syncDone(
# 43 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a2340, 
# 118 "/opt/tinyos-2.x/tos/interfaces/LogWrite.nc"
error_t arg_0x4078fe48);
#line 100
static  void Stm25pLogP$Write$default$eraseDone(
# 43 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a2340, 
# 100 "/opt/tinyos-2.x/tos/interfaces/LogWrite.nc"
error_t arg_0x4078f480);
#line 68
static  void Stm25pLogP$Write$default$appendDone(
# 43 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a2340, 
# 68 "/opt/tinyos-2.x/tos/interfaces/LogWrite.nc"
void *arg_0x40790010, storage_len_t arg_0x40790198, bool arg_0x40790320, 
error_t arg_0x407904b0);
# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t Stm25pLogP$ClientResource$default$release(
# 46 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a8360);
# 78 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t Stm25pLogP$ClientResource$default$request(
# 46 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a8360);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void Stm25pLogP$ClientResource$granted(
# 46 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a8360);
# 83 "/opt/tinyos-2.x/tos/interfaces/SplitControl.nc"
static  error_t Stm25pSectorP$SplitControl$start(void);
#line 109
static  error_t Stm25pSectorP$SplitControl$stop(void);
# 68 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
static  error_t Stm25pSectorP$Sector$read(
# 44 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x4082a958, 
# 68 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
stm25p_addr_t arg_0x4079a330, uint8_t *arg_0x4079a4d0, stm25p_len_t arg_0x4079a658);
#line 101
static  void Stm25pSectorP$Sector$default$writeDone(
# 44 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x4082a958, 
# 101 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
stm25p_addr_t arg_0x407b1088, uint8_t *arg_0x407b1228, stm25p_len_t arg_0x407b13b0, 
error_t arg_0x407b1540);









static  error_t Stm25pSectorP$Sector$erase(
# 44 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x4082a958, 
# 112 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
uint8_t arg_0x407b1af8, uint8_t arg_0x407b1c80);








static  void Stm25pSectorP$Sector$default$eraseDone(
# 44 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x4082a958, 
# 121 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
uint8_t arg_0x407b0250, uint8_t arg_0x407b03d8, error_t arg_0x407b0558);
#line 144
static  void Stm25pSectorP$Sector$default$computeCrcDone(
# 44 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x4082a958, 
# 144 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
stm25p_addr_t arg_0x407af4e8, stm25p_len_t arg_0x407af670, 
uint16_t arg_0x407af808, error_t arg_0x407af988);
#line 91
static  error_t Stm25pSectorP$Sector$write(
# 44 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x4082a958, 
# 91 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
stm25p_addr_t arg_0x407b3768, uint8_t *arg_0x407b3908, stm25p_len_t arg_0x407b3a90);
#line 56
static  uint8_t Stm25pSectorP$Sector$getNumSectors(
# 44 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x4082a958);
# 78 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
static  void Stm25pSectorP$Sector$default$readDone(
# 44 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x4082a958, 
# 78 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
stm25p_addr_t arg_0x4079ac48, uint8_t *arg_0x4079ade8, stm25p_len_t arg_0x407b3010, 
error_t arg_0x407b31a0);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void Stm25pSectorP$Stm25pResource$granted(
# 47 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x40828d68);
# 48 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pVolume.nc"
static   volume_id_t Stm25pSectorP$Volume$default$getVolumeId(
# 45 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x40828778);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void Stm25pSectorP$SpiResource$granted(void);
# 144 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpi.nc"
static   void Stm25pSectorP$Spi$sectorEraseDone(uint8_t arg_0x408385b8, error_t arg_0x40838738);
#line 77
static   void Stm25pSectorP$Spi$readDone(stm25p_addr_t arg_0x40821c90, uint8_t *arg_0x40821e30, 
stm25p_len_t arg_0x4081f010, error_t arg_0x4081f190);
#line 125
static   void Stm25pSectorP$Spi$pageProgramDone(stm25p_addr_t arg_0x408394d8, uint8_t *arg_0x40839678, 
stm25p_len_t arg_0x40839810, error_t arg_0x40839990);
#line 101
static   void Stm25pSectorP$Spi$computeCrcDone(uint16_t arg_0x4083a0b0, stm25p_addr_t arg_0x4083a238, 
stm25p_len_t arg_0x4083a3d0, error_t arg_0x4083a550);
#line 159
static   void Stm25pSectorP$Spi$bulkEraseDone(error_t arg_0x408360b8);
# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t Stm25pSectorP$ClientResource$release(
# 43 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x4082a010);
# 78 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t Stm25pSectorP$ClientResource$request(
# 43 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x4082a010);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void Stm25pSectorP$ClientResource$default$granted(
# 43 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x4082a010);
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void Stm25pSectorP$signalDone_task$runTask(void);
# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
static  error_t /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$Init$init(void);
# 69 "/opt/tinyos-2.x/tos/interfaces/ResourceQueue.nc"
static   error_t /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$FcfsQueue$enqueue(resource_client_id_t arg_0x4088c458);
#line 43
static   bool /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$FcfsQueue$isEmpty(void);








static   bool /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$FcfsQueue$isEnqueued(resource_client_id_t arg_0x4088da68);







static   resource_client_id_t /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$FcfsQueue$dequeue(void);
# 43 "/opt/tinyos-2.x/tos/interfaces/ResourceRequested.nc"
static   void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceRequested$default$requested(
# 55 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089e830);
# 55 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
static   void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceConfigure$default$unconfigure(
# 60 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089c9b8);
# 49 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
static   void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceConfigure$default$configure(
# 60 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089c9b8);
# 56 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
static   error_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceDefaultOwner$release(void);
# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Resource$release(
# 54 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089ff00);
# 78 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Resource$request(
# 54 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089ff00);
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$grantedTask$runTask(void);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Compare$fired(void);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Timer$overflow(void);
# 92 "/opt/tinyos-2.x/tos/lib/timer/Alarm.nc"
static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Alarm$startAt(/*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Alarm$size_type arg_0x408f4c30, /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Alarm$size_type arg_0x408f4db8);
#line 62
static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Alarm$stop(void);
# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
static  error_t /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Init$init(void);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   void /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Msp430Timer$overflow(void);
# 53 "/opt/tinyos-2.x/tos/lib/timer/Counter.nc"
static   /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Counter$size_type /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Counter$get(void);






static   bool /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Counter$isOverflowPending(void);










static   void /*CounterMilli32C.Transform*/TransformCounterC$0$CounterFrom$overflow(void);
#line 53
static   /*CounterMilli32C.Transform*/TransformCounterC$0$Counter$size_type /*CounterMilli32C.Transform*/TransformCounterC$0$Counter$get(void);
# 98 "/opt/tinyos-2.x/tos/lib/timer/Alarm.nc"
static   /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$size_type /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$getNow(void);
#line 92
static   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$startAt(/*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$size_type arg_0x408f4c30, /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$size_type arg_0x408f4db8);
#line 105
static   /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$size_type /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$getAlarm(void);
#line 62
static   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$stop(void);




static   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$AlarmFrom$fired(void);
# 71 "/opt/tinyos-2.x/tos/lib/timer/Counter.nc"
static   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Counter$overflow(void);
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$fired$runTask(void);
# 67 "/opt/tinyos-2.x/tos/lib/timer/Alarm.nc"
static   void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$fired(void);
# 125 "/opt/tinyos-2.x/tos/lib/timer/Timer.nc"
static  uint32_t /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Timer$getNow(void);
#line 118
static  void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Timer$startOneShotAt(uint32_t arg_0x408d90c0, uint32_t arg_0x408d9248);
#line 67
static  void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Timer$stop(void);
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$updateFromTimer$runTask(void);
# 72 "/opt/tinyos-2.x/tos/lib/timer/Timer.nc"
static  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$TimerFrom$fired(void);
#line 72
static  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer$default$fired(
# 37 "/opt/tinyos-2.x/tos/lib/timer/VirtualizeTimerC.nc"
uint8_t arg_0x409ae088);
# 62 "/opt/tinyos-2.x/tos/lib/timer/Timer.nc"
static  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer$startOneShot(
# 37 "/opt/tinyos-2.x/tos/lib/timer/VirtualizeTimerC.nc"
uint8_t arg_0x409ae088, 
# 62 "/opt/tinyos-2.x/tos/lib/timer/Timer.nc"
uint32_t arg_0x408dc490);




static  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer$stop(
# 37 "/opt/tinyos-2.x/tos/lib/timer/VirtualizeTimerC.nc"
uint8_t arg_0x409ae088);
# 71 "/opt/tinyos-2.x/tos/lib/timer/Counter.nc"
static   void /*HilTimerMilliC.CounterToLocalTimeC*/CounterToLocalTimeC$0$Counter$overflow(void);
# 92 "/opt/tinyos-2.x/tos/interfaces/SplitControl.nc"
static  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$SplitControl$startDone(error_t arg_0x40620638);
#line 117
static  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$SplitControl$stopDone(error_t arg_0x4061f1e0);
# 72 "/opt/tinyos-2.x/tos/lib/timer/Timer.nc"
static  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$TimerMilli$fired(void);
# 52 "/opt/tinyos-2.x/tos/lib/power/PowerDownCleanup.nc"
static   void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$PowerDownCleanup$default$cleanup(void);
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$timerTask$runTask(void);
# 73 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
static   void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$ResourceDefaultOwner$requested(void);
#line 46
static   void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$ResourceDefaultOwner$granted(void);
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$startTask$runTask(void);
# 74 "/opt/tinyos-2.x/tos/interfaces/StdControl.nc"
static  error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$StdControl$default$start(void);









static  error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$StdControl$default$stop(void);
# 71 "/opt/tinyos-2.x/tos/interfaces/SpiPacket.nc"
static   void Stm25pSpiP$SpiPacket$sendDone(uint8_t *arg_0x40a0ed80, uint8_t *arg_0x40a0ef20, uint16_t arg_0x40a0d0c0, 
error_t arg_0x40a0d250);
# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
static  error_t Stm25pSpiP$Init$init(void);
# 47 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpi.nc"
static   error_t Stm25pSpiP$Spi$powerDown(void);
#line 66
static   error_t Stm25pSpiP$Spi$read(stm25p_addr_t arg_0x40821350, uint8_t *arg_0x408214f0, 
stm25p_len_t arg_0x40821688);
#line 136
static   error_t Stm25pSpiP$Spi$sectorErase(uint8_t arg_0x40838010);
#line 55
static   error_t Stm25pSpiP$Spi$powerUp(void);
#line 114
static   error_t Stm25pSpiP$Spi$pageProgram(stm25p_addr_t arg_0x4083ab50, uint8_t *arg_0x4083acf0, 
stm25p_len_t arg_0x4083ae88);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void Stm25pSpiP$SpiResource$granted(void);
#line 110
static   error_t Stm25pSpiP$ClientResource$release(void);
#line 78
static   error_t Stm25pSpiP$ClientResource$request(void);
# 55 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$ResourceConfigure$unconfigure(
# 41 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
uint8_t arg_0x40a7edb0);
# 49 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$ResourceConfigure$configure(
# 41 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
uint8_t arg_0x40a7edb0);
# 59 "/opt/tinyos-2.x/tos/interfaces/SpiPacket.nc"
static   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$SpiPacket$send(
# 43 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
uint8_t arg_0x40a7d790, 
# 59 "/opt/tinyos-2.x/tos/interfaces/SpiPacket.nc"
uint8_t *arg_0x40a0e320, uint8_t *arg_0x40a0e4c0, uint16_t arg_0x40a0e648);
#line 71
static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$SpiPacket$default$sendDone(
# 43 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
uint8_t arg_0x40a7d790, 
# 71 "/opt/tinyos-2.x/tos/interfaces/SpiPacket.nc"
uint8_t *arg_0x40a0ed80, uint8_t *arg_0x40a0ef20, uint16_t arg_0x40a0d0c0, 
error_t arg_0x40a0d250);
# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiConfigure.nc"
static   msp430_spi_union_config_t */*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Msp430SpiConfigure$default$getConfig(
# 46 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
uint8_t arg_0x40a7c850);
# 34 "/opt/tinyos-2.x/tos/interfaces/SpiByte.nc"
static   uint8_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$SpiByte$write(uint8_t arg_0x40a12a18);
# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$default$release(
# 45 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
uint8_t arg_0x40a7de58);
# 78 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$default$request(
# 45 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
uint8_t arg_0x40a7de58);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$granted(
# 45 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
uint8_t arg_0x40a7de58);
# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Resource$release(
# 40 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
uint8_t arg_0x40a7e488);
# 78 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Resource$request(
# 40 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
uint8_t arg_0x40a7e488);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Resource$default$granted(
# 40 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
uint8_t arg_0x40a7e488);
# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartInterrupts$rxDone(uint8_t arg_0x40a82ae8);
#line 49
static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartInterrupts$txDone(void);
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$signalDone_task$runTask(void);
# 180 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
static   void HplMsp430Usart0P$Usart$enableRxIntr(void);
#line 197
static   void HplMsp430Usart0P$Usart$clrRxIntr(void);
#line 97
static   void HplMsp430Usart0P$Usart$resetUsart(bool arg_0x40a98a30);
#line 179
static   void HplMsp430Usart0P$Usart$disableIntr(void);
#line 90
static   void HplMsp430Usart0P$Usart$setUmctl(uint8_t arg_0x40a98220);
#line 177
static   void HplMsp430Usart0P$Usart$disableRxIntr(void);









static   bool HplMsp430Usart0P$Usart$isTxIntrPending(void);
#line 207
static   void HplMsp430Usart0P$Usart$clrIntr(void);
#line 80
static   void HplMsp430Usart0P$Usart$setUbr(uint16_t arg_0x40a749d0);
#line 224
static   void HplMsp430Usart0P$Usart$tx(uint8_t arg_0x40a8fc18);
#line 128
static   void HplMsp430Usart0P$Usart$disableUart(void);
#line 153
static   void HplMsp430Usart0P$Usart$enableSpi(void);
#line 168
static   void HplMsp430Usart0P$Usart$setModeSpi(msp430_spi_union_config_t *arg_0x40a94aa0);
#line 231
static   uint8_t HplMsp430Usart0P$Usart$rx(void);
#line 192
static   bool HplMsp430Usart0P$Usart$isRxIntrPending(void);









static   void HplMsp430Usart0P$Usart$clrTxIntr(void);
#line 158
static   void HplMsp430Usart0P$Usart$disableSpi(void);
# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
static   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$Interrupts$default$rxDone(
# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
uint8_t arg_0x40b38d48, 
# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
uint8_t arg_0x40a82ae8);
#line 49
static   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$Interrupts$default$txDone(
# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
uint8_t arg_0x40b38d48);
# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430I2CInterrupts.nc"
static   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$RawI2CInterrupts$fired(void);
#line 39
static   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$I2CInterrupts$default$fired(
# 40 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
uint8_t arg_0x40b37468);
# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
static   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$RawInterrupts$rxDone(uint8_t arg_0x40a82ae8);
#line 49
static   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$RawInterrupts$txDone(void);
# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
static  error_t /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$Init$init(void);
# 69 "/opt/tinyos-2.x/tos/interfaces/ResourceQueue.nc"
static   error_t /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$FcfsQueue$enqueue(resource_client_id_t arg_0x4088c458);
#line 43
static   bool /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$FcfsQueue$isEmpty(void);








static   bool /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$FcfsQueue$isEnqueued(resource_client_id_t arg_0x4088da68);







static   resource_client_id_t /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$FcfsQueue$dequeue(void);
# 43 "/opt/tinyos-2.x/tos/interfaces/ResourceRequested.nc"
static   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceRequested$default$requested(
# 55 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089e830);
# 55 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
static   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceConfigure$default$unconfigure(
# 60 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089c9b8);
# 49 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
static   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceConfigure$default$configure(
# 60 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089c9b8);
# 56 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
static   error_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$release(void);
#line 73
static   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$default$requested(void);
#line 46
static   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$default$granted(void);
# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Resource$release(
# 54 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089ff00);
# 78 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Resource$request(
# 54 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089ff00);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Resource$default$granted(
# 54 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089ff00);
# 80 "/opt/tinyos-2.x/tos/interfaces/ArbiterInfo.nc"
static   bool /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ArbiterInfo$inUse(void);







static   uint8_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ArbiterInfo$userId(void);
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$grantedTask$runTask(void);
# 7 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430I2C.nc"
static   void HplMsp430I2C0P$HplI2C$clearModeI2C(void);
#line 6
static   bool HplMsp430I2C0P$HplI2C$isI2C(void);
# 35 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
static   void /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$GeneralIO$makeOutput(void);
#line 29
static   void /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$GeneralIO$set(void);
static   void /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$GeneralIO$clr(void);




static   void /*HplStm25pPinsC.HoldM*/Msp430GpioC$4$GeneralIO$makeOutput(void);
#line 29
static   void /*HplStm25pPinsC.HoldM*/Msp430GpioC$4$GeneralIO$set(void);
# 48 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pVolume.nc"
static   volume_id_t /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC.BinderP*/Stm25pBinderP$0$Volume$getVolumeId(void);
# 89 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
static  void /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$SubSend$sendDone(message_t *arg_0x40bf6dd8, error_t arg_0x40bf5010);
# 67 "/opt/tinyos-2.x/tos/interfaces/Receive.nc"
static  message_t */*SerialActiveMessageC.AM*/SerialActiveMessageP$0$SubReceive$receive(message_t *arg_0x406437d0, void *arg_0x40643968, uint8_t arg_0x40643ae8);
# 69 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
static  error_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMSend$send(
# 36 "/opt/tinyos-2.x/tos/lib/serial/SerialActiveMessageP.nc"
am_id_t arg_0x40c00c98, 
# 69 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
am_addr_t arg_0x40637b40, message_t *arg_0x40637ce8, uint8_t arg_0x40637e68);
# 67 "/opt/tinyos-2.x/tos/interfaces/Packet.nc"
static  uint8_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Packet$payloadLength(message_t *arg_0x40652010);
#line 106
static  void */*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Packet$getPayload(message_t *arg_0x406513b8, uint8_t arg_0x40651538);
#line 95
static  uint8_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Packet$maxPayloadLength(void);
#line 83
static  void /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Packet$setPayloadLength(message_t *arg_0x40652670, uint8_t arg_0x406527f0);
# 67 "/opt/tinyos-2.x/tos/interfaces/Receive.nc"
static  message_t */*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Receive$default$receive(
# 37 "/opt/tinyos-2.x/tos/lib/serial/SerialActiveMessageP.nc"
am_id_t arg_0x40bff650, 
# 67 "/opt/tinyos-2.x/tos/interfaces/Receive.nc"
message_t *arg_0x406437d0, void *arg_0x40643968, uint8_t arg_0x40643ae8);
# 67 "/opt/tinyos-2.x/tos/interfaces/AMPacket.nc"
static  am_addr_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$destination(message_t *arg_0x40be5360);
#line 92
static  void /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$setDestination(message_t *arg_0x40be5ee8, am_addr_t arg_0x40be3088);
#line 136
static  am_id_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$type(message_t *arg_0x40be27c8);
#line 151
static  void /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$setType(message_t *arg_0x40be2d40, am_id_t arg_0x40be2ec0);
# 83 "/opt/tinyos-2.x/tos/interfaces/SplitControl.nc"
static  error_t SerialP$SplitControl$start(void);
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void SerialP$stopDoneTask$runTask(void);
#line 64
static  void SerialP$RunTx$runTask(void);
# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
static  error_t SerialP$Init$init(void);
# 43 "/opt/tinyos-2.x/tos/lib/serial/SerialFlush.nc"
static  void SerialP$SerialFlush$flushDone(void);
#line 38
static  void SerialP$SerialFlush$default$flush(void);
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void SerialP$startDoneTask$runTask(void);
# 83 "/opt/tinyos-2.x/tos/lib/serial/SerialFrameComm.nc"
static   void SerialP$SerialFrameComm$dataReceived(uint8_t arg_0x40c2e0f8);





static   void SerialP$SerialFrameComm$putDone(void);
#line 74
static   void SerialP$SerialFrameComm$delimiterReceived(void);
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void SerialP$defaultSerialFlushTask$runTask(void);
# 60 "/opt/tinyos-2.x/tos/lib/serial/SendBytePacket.nc"
static   error_t SerialP$SendBytePacket$completeSend(void);
#line 51
static   error_t SerialP$SendBytePacket$startSend(uint8_t arg_0x40c3a658);
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTask$runTask(void);
# 64 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
static  error_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Send$send(
# 40 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
uart_id_t arg_0x40cafa48, 
# 64 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
message_t *arg_0x40bf8a90, uint8_t arg_0x40bf8c10);
#line 89
static  void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Send$default$sendDone(
# 40 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
uart_id_t arg_0x40cafa48, 
# 89 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
message_t *arg_0x40bf6dd8, error_t arg_0x40bf5010);
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$signalSendDone$runTask(void);
# 67 "/opt/tinyos-2.x/tos/interfaces/Receive.nc"
static  message_t */*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Receive$default$receive(
# 39 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
uart_id_t arg_0x40caf4a8, 
# 67 "/opt/tinyos-2.x/tos/interfaces/Receive.nc"
message_t *arg_0x406437d0, void *arg_0x40643968, uint8_t arg_0x40643ae8);
# 31 "/opt/tinyos-2.x/tos/lib/serial/SerialPacketInfo.nc"
static   uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$default$upperLength(
# 43 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
uart_id_t arg_0x40cae450, 
# 31 "/opt/tinyos-2.x/tos/lib/serial/SerialPacketInfo.nc"
message_t *arg_0x40c197f0, uint8_t arg_0x40c19978);
#line 15
static   uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$default$offset(
# 43 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
uart_id_t arg_0x40cae450);
# 23 "/opt/tinyos-2.x/tos/lib/serial/SerialPacketInfo.nc"
static   uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$default$dataLinkLength(
# 43 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
uart_id_t arg_0x40cae450, 
# 23 "/opt/tinyos-2.x/tos/lib/serial/SerialPacketInfo.nc"
message_t *arg_0x40c19010, uint8_t arg_0x40c19198);
# 70 "/opt/tinyos-2.x/tos/lib/serial/SendBytePacket.nc"
static   uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SendBytePacket$nextByte(void);









static   void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SendBytePacket$sendCompleted(error_t arg_0x40c38650);
# 51 "/opt/tinyos-2.x/tos/lib/serial/ReceiveBytePacket.nc"
static   error_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$ReceiveBytePacket$startPacket(void);






static   void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$ReceiveBytePacket$byteReceived(uint8_t arg_0x40c347c0);










static   void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$ReceiveBytePacket$endPacket(error_t arg_0x40c34d80);
# 79 "/opt/tinyos-2.x/tos/interfaces/UartStream.nc"
static   void HdlcTranslateC$UartStream$receivedByte(uint8_t arg_0x40ccc6f8);
#line 99
static   void HdlcTranslateC$UartStream$receiveDone(uint8_t *arg_0x40cca3b8, uint16_t arg_0x40cca540, error_t arg_0x40cca6c0);
#line 57
static   void HdlcTranslateC$UartStream$sendDone(uint8_t *arg_0x40ccd608, uint16_t arg_0x40ccd790, error_t arg_0x40ccd910);
# 45 "/opt/tinyos-2.x/tos/lib/serial/SerialFrameComm.nc"
static   error_t HdlcTranslateC$SerialFrameComm$putDelimiter(void);
#line 68
static   void HdlcTranslateC$SerialFrameComm$resetReceive(void);
#line 54
static   error_t HdlcTranslateC$SerialFrameComm$putData(uint8_t arg_0x40c30e60);
# 55 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$ResourceConfigure$unconfigure(
# 43 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
uint8_t arg_0x40cf8088);
# 49 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$ResourceConfigure$configure(
# 43 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
uint8_t arg_0x40cf8088);
# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartConfigure.nc"
static   msp430_uart_union_config_t */*Msp430Uart1P.UartP*/Msp430UartP$0$Msp430UartConfigure$default$getConfig(
# 49 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
uint8_t arg_0x40d26d48);
# 48 "/opt/tinyos-2.x/tos/interfaces/UartStream.nc"
static   error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$UartStream$send(uint8_t *arg_0x40ccee50, uint16_t arg_0x40ccd010);
# 71 "/opt/tinyos-2.x/tos/lib/timer/Counter.nc"
static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Counter$overflow(void);
# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$default$release(
# 48 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
uint8_t arg_0x40d26420);
# 87 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$default$immediateRequest(
# 48 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
uint8_t arg_0x40d26420);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$granted(
# 48 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
uint8_t arg_0x40d26420);
# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$Resource$release(
# 42 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
uint8_t arg_0x40cf9738);
# 87 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$Resource$immediateRequest(
# 42 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
uint8_t arg_0x40cf9738);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void /*Msp430Uart1P.UartP*/Msp430UartP$0$Resource$default$granted(
# 42 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
uint8_t arg_0x40cf9738);
# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartInterrupts$rxDone(uint8_t arg_0x40a82ae8);
#line 49
static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartInterrupts$txDone(void);
# 143 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
static   void HplMsp430Usart1P$Usart$enableUartRx(void);
#line 123
static   void HplMsp430Usart1P$Usart$enableUart(void);
#line 97
static   void HplMsp430Usart1P$Usart$resetUsart(bool arg_0x40a98a30);
#line 179
static   void HplMsp430Usart1P$Usart$disableIntr(void);
#line 90
static   void HplMsp430Usart1P$Usart$setUmctl(uint8_t arg_0x40a98220);
#line 133
static   void HplMsp430Usart1P$Usart$enableUartTx(void);
#line 148
static   void HplMsp430Usart1P$Usart$disableUartRx(void);
#line 182
static   void HplMsp430Usart1P$Usart$enableIntr(void);
#line 207
static   void HplMsp430Usart1P$Usart$clrIntr(void);
#line 80
static   void HplMsp430Usart1P$Usart$setUbr(uint16_t arg_0x40a749d0);
#line 224
static   void HplMsp430Usart1P$Usart$tx(uint8_t arg_0x40a8fc18);
#line 128
static   void HplMsp430Usart1P$Usart$disableUart(void);
#line 174
static   void HplMsp430Usart1P$Usart$setModeUart(msp430_uart_union_config_t *arg_0x40a920a8);
#line 158
static   void HplMsp430Usart1P$Usart$disableSpi(void);
#line 138
static   void HplMsp430Usart1P$Usart$disableUartTx(void);
# 74 "/opt/tinyos-2.x/tos/interfaces/AsyncStdControl.nc"
static   error_t HplMsp430Usart1P$AsyncStdControl$start(void);









static   error_t HplMsp430Usart1P$AsyncStdControl$stop(void);
# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
static   void /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$Interrupts$default$rxDone(
# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
uint8_t arg_0x40b38d48, 
# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
uint8_t arg_0x40a82ae8);
#line 49
static   void /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$Interrupts$default$txDone(
# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
uint8_t arg_0x40b38d48);
# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
static   void /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$RawInterrupts$rxDone(uint8_t arg_0x40a82ae8);
#line 49
static   void /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$RawInterrupts$txDone(void);
# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
static  error_t /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$Init$init(void);
# 43 "/opt/tinyos-2.x/tos/interfaces/ResourceQueue.nc"
static   bool /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$FcfsQueue$isEmpty(void);
#line 60
static   resource_client_id_t /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$FcfsQueue$dequeue(void);
# 51 "/opt/tinyos-2.x/tos/interfaces/ResourceRequested.nc"
static   void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceRequested$default$immediateRequested(
# 55 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089e830);
# 55 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
static   void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceConfigure$default$unconfigure(
# 60 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089c9b8);
# 49 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
static   void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceConfigure$default$configure(
# 60 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089c9b8);
# 56 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
static   error_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceDefaultOwner$release(void);
# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Resource$release(
# 54 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089ff00);
# 87 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Resource$immediateRequest(
# 54 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089ff00);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Resource$default$granted(
# 54 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089ff00);
# 80 "/opt/tinyos-2.x/tos/interfaces/ArbiterInfo.nc"
static   bool /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ArbiterInfo$inUse(void);







static   uint8_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ArbiterInfo$userId(void);
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$grantedTask$runTask(void);
# 52 "/opt/tinyos-2.x/tos/lib/power/PowerDownCleanup.nc"
static   void /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$PowerDownCleanup$default$cleanup(void);
# 46 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
static   void /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$ResourceDefaultOwner$granted(void);
#line 81
static   void /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$ResourceDefaultOwner$immediateRequested(void);
# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartConfigure.nc"
static   msp430_uart_union_config_t *TelosSerialP$Msp430UartConfigure$getConfig(void);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void TelosSerialP$Resource$granted(void);
# 74 "/opt/tinyos-2.x/tos/interfaces/StdControl.nc"
static  error_t TelosSerialP$StdControl$start(void);









static  error_t TelosSerialP$StdControl$stop(void);
# 31 "/opt/tinyos-2.x/tos/lib/serial/SerialPacketInfo.nc"
static   uint8_t SerialPacketInfoActiveMessageP$Info$upperLength(message_t *arg_0x40c197f0, uint8_t arg_0x40c19978);
#line 15
static   uint8_t SerialPacketInfoActiveMessageP$Info$offset(void);







static   uint8_t SerialPacketInfoActiveMessageP$Info$dataLinkLength(message_t *arg_0x40c19010, uint8_t arg_0x40c19198);
# 69 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
static  error_t /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$AMSend$send(am_addr_t arg_0x40637b40, message_t *arg_0x40637ce8, uint8_t arg_0x40637e68);
# 89 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
static  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$Send$sendDone(message_t *arg_0x40bf6dd8, error_t arg_0x40bf5010);
# 99 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
static  void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$AMSend$sendDone(
# 40 "/opt/tinyos-2.x/tos/system/AMQueueImplP.nc"
am_id_t arg_0x40defd10, 
# 99 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
message_t *arg_0x40636ea0, error_t arg_0x40635030);
# 64 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
static  error_t /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Send$send(
# 38 "/opt/tinyos-2.x/tos/system/AMQueueImplP.nc"
uint8_t arg_0x40def3e8, 
# 64 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
message_t *arg_0x40bf8a90, uint8_t arg_0x40bf8c10);
#line 89
static  void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Send$default$sendDone(
# 38 "/opt/tinyos-2.x/tos/system/AMQueueImplP.nc"
uint8_t arg_0x40def3e8, 
# 89 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
message_t *arg_0x40bf6dd8, error_t arg_0x40bf5010);
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$errorTask$runTask(void);
#line 64
static  void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$CancelTask$runTask(void);
# 69 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
static  error_t /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$AMSend$send(am_addr_t arg_0x40637b40, message_t *arg_0x40637ce8, uint8_t arg_0x40637e68);
# 89 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
static  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$Send$sendDone(message_t *arg_0x40bf6dd8, error_t arg_0x40bf5010);
# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
static  error_t PlatformP$MoteInit$init(void);
#line 51
static  error_t PlatformP$MoteClockInit$init(void);
#line 51
static  error_t PlatformP$LedsInit$init(void);
# 10 "/opt/tinyos-2.x/tos/platforms/telosa/PlatformP.nc"
static inline  error_t PlatformP$Init$init(void);
# 6 "/opt/tinyos-2.x/tos/platforms/telosb/MotePlatformC.nc"
static __inline void MotePlatformC$uwait(uint16_t u);




static __inline void MotePlatformC$TOSH_wait(void);




static void MotePlatformC$TOSH_FLASH_M25P_DP_bit(bool set);










static inline void MotePlatformC$TOSH_FLASH_M25P_DP(void);
#line 56
static inline  error_t MotePlatformC$Init$init(void);
# 32 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430ClockInit.nc"
static  void Msp430ClockP$Msp430ClockInit$initTimerB(void);
#line 31
static  void Msp430ClockP$Msp430ClockInit$initTimerA(void);
#line 29
static  void Msp430ClockP$Msp430ClockInit$setupDcoCalibrate(void);
static  void Msp430ClockP$Msp430ClockInit$initClocks(void);
# 39 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430ClockP.nc"
 static volatile uint8_t Msp430ClockP$IE1 __asm ("0x0000");
 static volatile uint16_t Msp430ClockP$TA0CTL __asm ("0x0160");
 static volatile uint16_t Msp430ClockP$TA0IV __asm ("0x012E");
 static volatile uint16_t Msp430ClockP$TBCTL __asm ("0x0180");
 static volatile uint16_t Msp430ClockP$TBIV __asm ("0x011E");

enum Msp430ClockP$__nesc_unnamed4301 {

  Msp430ClockP$ACLK_CALIB_PERIOD = 8, 
  Msp430ClockP$TARGET_DCO_DELTA = 4096 / 32 * Msp430ClockP$ACLK_CALIB_PERIOD
};


static inline  void Msp430ClockP$Msp430ClockInit$defaultSetupDcoCalibrate(void);
#line 64
static inline  void Msp430ClockP$Msp430ClockInit$defaultInitClocks(void);
#line 85
static inline  void Msp430ClockP$Msp430ClockInit$defaultInitTimerA(void);
#line 100
static inline  void Msp430ClockP$Msp430ClockInit$defaultInitTimerB(void);
#line 115
static inline   void Msp430ClockP$Msp430ClockInit$default$setupDcoCalibrate(void);




static inline   void Msp430ClockP$Msp430ClockInit$default$initClocks(void);




static inline   void Msp430ClockP$Msp430ClockInit$default$initTimerA(void);




static inline   void Msp430ClockP$Msp430ClockInit$default$initTimerB(void);





static inline void Msp430ClockP$startTimerA(void);
#line 148
static inline void Msp430ClockP$startTimerB(void);
#line 160
static void Msp430ClockP$set_dco_calib(int calib);





static inline uint16_t Msp430ClockP$test_calib_busywait_delta(int calib);
#line 189
static inline void Msp430ClockP$busyCalibrateDco(void);
#line 214
static inline  error_t Msp430ClockP$Init$init(void);
# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
static   void /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$Event$fired(
# 40 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerP.nc"
uint8_t arg_0x4056f4f0);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   void /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$Timer$overflow(void);
# 115 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerP.nc"
static inline   void /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$VectorTimerX0$fired(void);




static inline   void /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$VectorTimerX1$fired(void);





static inline   void /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$Overflow$fired(void);








static inline    void /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$Event$default$fired(uint8_t n);
# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
static   void /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Event$fired(
# 40 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerP.nc"
uint8_t arg_0x4056f4f0);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   void /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Timer$overflow(void);
# 51 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerP.nc"
static   uint16_t /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Timer$get(void);
#line 70
static inline   bool /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Timer$isOverflowPending(void);
#line 115
static inline   void /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$VectorTimerX0$fired(void);




static inline   void /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$VectorTimerX1$fired(void);





static inline   void /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Overflow$fired(void);








static    void /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Event$default$fired(uint8_t n);
# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   void /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Capture$captured(uint16_t arg_0x40557100);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Compare$fired(void);
# 44 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
typedef msp430_compare_control_t /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$cc_t;


static inline /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$cc_t /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$int2CC(uint16_t x);
#line 74
static inline   /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$cc_t /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Control$getControl(void);
#line 139
static inline   uint16_t /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Capture$getEvent(void);
#line 169
static   void /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Event$fired(void);







static inline    void /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Capture$default$captured(uint16_t n);



static inline    void /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Compare$default$fired(void);



static inline   void /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Timer$overflow(void);
# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   void /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Capture$captured(uint16_t arg_0x40557100);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Compare$fired(void);
# 44 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
typedef msp430_compare_control_t /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$cc_t;


static inline /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$cc_t /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$int2CC(uint16_t x);
#line 74
static inline   /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$cc_t /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Control$getControl(void);
#line 139
static inline   uint16_t /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Capture$getEvent(void);
#line 169
static   void /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Event$fired(void);







static inline    void /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Capture$default$captured(uint16_t n);



static inline    void /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Compare$default$fired(void);



static inline   void /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Timer$overflow(void);
# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   void /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Capture$captured(uint16_t arg_0x40557100);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Compare$fired(void);
# 44 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
typedef msp430_compare_control_t /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$cc_t;


static inline /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$cc_t /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$int2CC(uint16_t x);
#line 74
static inline   /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$cc_t /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Control$getControl(void);
#line 139
static inline   uint16_t /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Capture$getEvent(void);
#line 169
static   void /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Event$fired(void);







static inline    void /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Capture$default$captured(uint16_t n);



static inline    void /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Compare$default$fired(void);



static inline   void /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Timer$overflow(void);
# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Capture$captured(uint16_t arg_0x40557100);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Compare$fired(void);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   uint16_t /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Timer$get(void);
# 44 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
typedef msp430_compare_control_t /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$cc_t;

static inline uint16_t /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$CC2int(/*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$cc_t x);
static inline /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$cc_t /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$int2CC(uint16_t x);

static inline uint16_t /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$compareControl(void);
#line 74
static inline   /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$cc_t /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$getControl(void);









static inline   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$clearPendingInterrupt(void);









static inline   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$setControlAsCompare(void);
#line 119
static inline   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$enableEvents(void);




static inline   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$disableEvents(void);
#line 139
static inline   uint16_t /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Capture$getEvent(void);




static inline   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Compare$setEvent(uint16_t x);









static inline   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Compare$setEventFromNow(uint16_t x);
#line 169
static inline   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Event$fired(void);







static inline    void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Capture$default$captured(uint16_t n);







static inline   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Timer$overflow(void);
# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   void /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Capture$captured(uint16_t arg_0x40557100);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Compare$fired(void);
# 44 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
typedef msp430_compare_control_t /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$cc_t;


static inline /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$cc_t /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$int2CC(uint16_t x);
#line 74
static inline   /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$cc_t /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Control$getControl(void);
#line 139
static inline   uint16_t /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Capture$getEvent(void);
#line 169
static inline   void /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Event$fired(void);







static inline    void /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Capture$default$captured(uint16_t n);



static inline    void /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Compare$default$fired(void);



static inline   void /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Timer$overflow(void);
# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   void /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Capture$captured(uint16_t arg_0x40557100);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Compare$fired(void);
# 44 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
typedef msp430_compare_control_t /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$cc_t;


static inline /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$cc_t /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$int2CC(uint16_t x);
#line 74
static inline   /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$cc_t /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Control$getControl(void);
#line 139
static inline   uint16_t /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Capture$getEvent(void);
#line 169
static inline   void /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Event$fired(void);







static inline    void /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Capture$default$captured(uint16_t n);



static inline    void /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Compare$default$fired(void);



static inline   void /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Timer$overflow(void);
# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   void /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Capture$captured(uint16_t arg_0x40557100);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Compare$fired(void);
# 44 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
typedef msp430_compare_control_t /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$cc_t;


static inline /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$cc_t /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$int2CC(uint16_t x);
#line 74
static inline   /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$cc_t /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Control$getControl(void);
#line 139
static inline   uint16_t /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Capture$getEvent(void);
#line 169
static inline   void /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Event$fired(void);







static inline    void /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Capture$default$captured(uint16_t n);



static inline    void /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Compare$default$fired(void);



static inline   void /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Timer$overflow(void);
# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   void /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Capture$captured(uint16_t arg_0x40557100);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Compare$fired(void);
# 44 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
typedef msp430_compare_control_t /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$cc_t;


static inline /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$cc_t /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$int2CC(uint16_t x);
#line 74
static inline   /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$cc_t /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Control$getControl(void);
#line 139
static inline   uint16_t /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Capture$getEvent(void);
#line 169
static inline   void /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Event$fired(void);







static inline    void /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Capture$default$captured(uint16_t n);



static inline    void /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Compare$default$fired(void);



static inline   void /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Timer$overflow(void);
# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   void /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Capture$captured(uint16_t arg_0x40557100);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Compare$fired(void);
# 44 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
typedef msp430_compare_control_t /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$cc_t;


static inline /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$cc_t /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$int2CC(uint16_t x);
#line 74
static inline   /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$cc_t /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Control$getControl(void);
#line 139
static inline   uint16_t /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Capture$getEvent(void);
#line 169
static inline   void /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Event$fired(void);







static inline    void /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Capture$default$captured(uint16_t n);



static inline    void /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Compare$default$fired(void);



static inline   void /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Timer$overflow(void);
# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
static   void /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Capture$captured(uint16_t arg_0x40557100);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Compare$fired(void);
# 44 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
typedef msp430_compare_control_t /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$cc_t;


static inline /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$cc_t /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$int2CC(uint16_t x);
#line 74
static inline   /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$cc_t /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Control$getControl(void);
#line 139
static inline   uint16_t /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Capture$getEvent(void);
#line 169
static inline   void /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Event$fired(void);







static inline    void /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Capture$default$captured(uint16_t n);



static inline    void /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Compare$default$fired(void);



static inline   void /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Timer$overflow(void);
# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
static   void Msp430TimerCommonP$VectorTimerB1$fired(void);
#line 28
static   void Msp430TimerCommonP$VectorTimerA0$fired(void);
#line 28
static   void Msp430TimerCommonP$VectorTimerA1$fired(void);
#line 28
static   void Msp430TimerCommonP$VectorTimerB0$fired(void);
# 11 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCommonP.nc"
void sig_TIMERA0_VECTOR(void) __attribute((wakeup)) __attribute((interrupt(12)))  ;
void sig_TIMERA1_VECTOR(void) __attribute((wakeup)) __attribute((interrupt(10)))  ;
void sig_TIMERB0_VECTOR(void) __attribute((wakeup)) __attribute((interrupt(26)))  ;
void sig_TIMERB1_VECTOR(void) __attribute((wakeup)) __attribute((interrupt(24)))  ;
# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
static  error_t RealMainP$SoftwareInit$init(void);
# 49 "/opt/tinyos-2.x/tos/interfaces/Boot.nc"
static  void RealMainP$Boot$booted(void);
# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
static  error_t RealMainP$PlatformInit$init(void);
# 46 "/opt/tinyos-2.x/tos/interfaces/Scheduler.nc"
static  void RealMainP$Scheduler$init(void);
#line 61
static  void RealMainP$Scheduler$taskLoop(void);
#line 54
static  bool RealMainP$Scheduler$runNextTask(void);
# 52 "/opt/tinyos-2.x/tos/system/RealMainP.nc"
int main(void)   ;
# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void SchedulerBasicP$TaskBasic$runTask(
# 45 "/opt/tinyos-2.x/tos/system/SchedulerBasicP.nc"
uint8_t arg_0x404aa1e0);
# 59 "/opt/tinyos-2.x/tos/interfaces/McuSleep.nc"
static   void SchedulerBasicP$McuSleep$sleep(void);
# 50 "/opt/tinyos-2.x/tos/system/SchedulerBasicP.nc"
enum SchedulerBasicP$__nesc_unnamed4302 {

  SchedulerBasicP$NUM_TASKS = 17U, 
  SchedulerBasicP$NO_TASK = 255
};

volatile uint8_t SchedulerBasicP$m_head;
volatile uint8_t SchedulerBasicP$m_tail;
volatile uint8_t SchedulerBasicP$m_next[SchedulerBasicP$NUM_TASKS];








static __inline uint8_t SchedulerBasicP$popTask(void);
#line 86
static inline bool SchedulerBasicP$isWaiting(uint8_t id);




static inline bool SchedulerBasicP$pushTask(uint8_t id);
#line 113
static inline  void SchedulerBasicP$Scheduler$init(void);









static  bool SchedulerBasicP$Scheduler$runNextTask(void);
#line 138
static inline  void SchedulerBasicP$Scheduler$taskLoop(void);
#line 159
static   error_t SchedulerBasicP$TaskBasic$postTask(uint8_t id);




static   void SchedulerBasicP$TaskBasic$default$runTask(uint8_t id);
# 54 "/opt/tinyos-2.x/tos/interfaces/McuPowerOverride.nc"
static   mcu_power_t McuSleepC$McuPowerOverride$lowestState(void);
# 51 "/opt/tinyos-2.x/tos/chips/msp430/McuSleepC.nc"
bool McuSleepC$dirty = TRUE;
mcu_power_t McuSleepC$powerState = MSP430_POWER_ACTIVE;




const uint16_t McuSleepC$msp430PowerBits[MSP430_POWER_LPM4 + 1] = { 
0, 
0x0010, 
0x0040 + 0x0010, 
0x0080 + 0x0010, 
0x0080 + 0x0040 + 0x0010, 
0x0080 + 0x0040 + 0x0020 + 0x0010 };


static inline mcu_power_t McuSleepC$getPowerState(void);
#line 104
static inline void McuSleepC$computePowerState(void);




static inline   void McuSleepC$McuSleep$sleep(void);
#line 124
static inline    mcu_power_t McuSleepC$McuPowerOverride$default$lowestState(void);
# 83 "/opt/tinyos-2.x/tos/interfaces/SplitControl.nc"
static  error_t TraceRetrieverP$AMControl$start(void);
# 106 "/opt/tinyos-2.x/tos/interfaces/Packet.nc"
static  void *TraceRetrieverP$Packet$getPayload(message_t *arg_0x406513b8, uint8_t arg_0x40651538);
# 69 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
static  error_t TraceRetrieverP$UARTSendData$send(am_addr_t arg_0x40637b40, message_t *arg_0x40637ce8, uint8_t arg_0x40637e68);
# 72 "/opt/tinyos-2.x/tos/interfaces/Leds.nc"
static   void TraceRetrieverP$Leds$led1Toggle(void);
#line 45
static   void TraceRetrieverP$Leds$led0On(void);
# 63 "/opt/tinyos-2.x/tos/interfaces/LogRead.nc"
static  error_t TraceRetrieverP$LogRead$read(void *arg_0x40614e10, storage_len_t arg_0x40613010);
# 69 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
static  error_t TraceRetrieverP$UARTSendAck$send(am_addr_t arg_0x40637b40, message_t *arg_0x40637ce8, uint8_t arg_0x40637e68);
# 45 "TraceRetrieverP.nc"
static __inline void TraceRetrieverP$readNextLog(void);
static __inline void TraceRetrieverP$sendLogData(void);


TraceLogEntry TraceRetrieverP$traceLogEntry;
TraceEntry *TraceRetrieverP$logEntry;
bool TraceRetrieverP$reading;
uint16_t TraceRetrieverP$logIndex;
message_t TraceRetrieverP$ack;
message_t TraceRetrieverP$logData;


static inline  void TraceRetrieverP$Boot$booted(void);







static inline  void TraceRetrieverP$AMControl$startDone(error_t err);








static inline  message_t *TraceRetrieverP$UARTReceive$receive(message_t *bufPtr, void *payload, 
uint8_t length);







static __inline void TraceRetrieverP$readNextLog(void);








static inline  void TraceRetrieverP$LogRead$readDone(void *buf, storage_len_t length, error_t err);
#line 106
static __inline void TraceRetrieverP$sendLogData(void);





static  void TraceRetrieverP$UARTSendData$sendDone(message_t *bufPtr, error_t error);










static inline  void TraceRetrieverP$UARTSendAck$sendDone(message_t *bufPtr, error_t error);





static inline  void TraceRetrieverP$LogRead$seekDone(error_t err);
static inline  void TraceRetrieverP$AMControl$stopDone(error_t err);
# 35 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
static   void LedsP$Led0$makeOutput(void);
#line 29
static   void LedsP$Led0$set(void);
static   void LedsP$Led0$clr(void);
static   void LedsP$Led1$toggle(void);



static   void LedsP$Led1$makeOutput(void);
#line 29
static   void LedsP$Led1$set(void);





static   void LedsP$Led2$makeOutput(void);
#line 29
static   void LedsP$Led2$set(void);
# 45 "/opt/tinyos-2.x/tos/system/LedsP.nc"
static inline  error_t LedsP$Init$init(void);
#line 63
static inline   void LedsP$Leds$led0On(void);
#line 88
static inline   void LedsP$Leds$led1Toggle(void);
# 54 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P31*/HplMsp430GeneralIOP$17$IO$selectModuleFunc(void);

static inline   void /*HplMsp430GeneralIOC.P31*/HplMsp430GeneralIOP$17$IO$selectIOFunc(void);
#line 54
static inline   void /*HplMsp430GeneralIOC.P32*/HplMsp430GeneralIOP$18$IO$selectModuleFunc(void);

static inline   void /*HplMsp430GeneralIOC.P32*/HplMsp430GeneralIOP$18$IO$selectIOFunc(void);
#line 54
static inline   void /*HplMsp430GeneralIOC.P33*/HplMsp430GeneralIOP$19$IO$selectModuleFunc(void);

static inline   void /*HplMsp430GeneralIOC.P33*/HplMsp430GeneralIOP$19$IO$selectIOFunc(void);
#line 56
static inline   void /*HplMsp430GeneralIOC.P34*/HplMsp430GeneralIOP$20$IO$selectIOFunc(void);
#line 56
static inline   void /*HplMsp430GeneralIOC.P35*/HplMsp430GeneralIOP$21$IO$selectIOFunc(void);
#line 54
static inline   void /*HplMsp430GeneralIOC.P36*/HplMsp430GeneralIOP$22$IO$selectModuleFunc(void);

static inline   void /*HplMsp430GeneralIOC.P36*/HplMsp430GeneralIOP$22$IO$selectIOFunc(void);
#line 54
static inline   void /*HplMsp430GeneralIOC.P37*/HplMsp430GeneralIOP$23$IO$selectModuleFunc(void);

static inline   void /*HplMsp430GeneralIOC.P37*/HplMsp430GeneralIOP$23$IO$selectIOFunc(void);
#line 45
static   void /*HplMsp430GeneralIOC.P44*/HplMsp430GeneralIOP$28$IO$set(void);
static   void /*HplMsp430GeneralIOC.P44*/HplMsp430GeneralIOP$28$IO$clr(void);





static inline   void /*HplMsp430GeneralIOC.P44*/HplMsp430GeneralIOP$28$IO$makeOutput(void);
#line 45
static inline   void /*HplMsp430GeneralIOC.P47*/HplMsp430GeneralIOP$31$IO$set(void);






static inline   void /*HplMsp430GeneralIOC.P47*/HplMsp430GeneralIOP$31$IO$makeOutput(void);



static inline   void /*HplMsp430GeneralIOC.P51*/HplMsp430GeneralIOP$33$IO$selectIOFunc(void);
#line 56
static inline   void /*HplMsp430GeneralIOC.P52*/HplMsp430GeneralIOP$34$IO$selectIOFunc(void);
#line 56
static inline   void /*HplMsp430GeneralIOC.P53*/HplMsp430GeneralIOP$35$IO$selectIOFunc(void);
#line 45
static inline   void /*HplMsp430GeneralIOC.P54*/HplMsp430GeneralIOP$36$IO$set(void);
static   void /*HplMsp430GeneralIOC.P54*/HplMsp430GeneralIOP$36$IO$clr(void);





static inline   void /*HplMsp430GeneralIOC.P54*/HplMsp430GeneralIOP$36$IO$makeOutput(void);
#line 45
static inline   void /*HplMsp430GeneralIOC.P55*/HplMsp430GeneralIOP$37$IO$set(void);

static   void /*HplMsp430GeneralIOC.P55*/HplMsp430GeneralIOP$37$IO$toggle(void);




static inline   void /*HplMsp430GeneralIOC.P55*/HplMsp430GeneralIOP$37$IO$makeOutput(void);
#line 45
static inline   void /*HplMsp430GeneralIOC.P56*/HplMsp430GeneralIOP$38$IO$set(void);






static inline   void /*HplMsp430GeneralIOC.P56*/HplMsp430GeneralIOP$38$IO$makeOutput(void);
# 71 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
static   void /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$HplGeneralIO$makeOutput(void);
#line 34
static   void /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$HplGeneralIO$set(void);




static   void /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$HplGeneralIO$clr(void);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$GeneralIO$set(void);
static inline   void /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$GeneralIO$clr(void);




static inline   void /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$GeneralIO$makeOutput(void);
# 44 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
static   void /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$HplGeneralIO$toggle(void);
#line 71
static   void /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$HplGeneralIO$makeOutput(void);
#line 34
static   void /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$HplGeneralIO$set(void);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$GeneralIO$set(void);

static inline   void /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$GeneralIO$toggle(void);



static inline   void /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$GeneralIO$makeOutput(void);
# 71 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
static   void /*PlatformLedsC.Led2Impl*/Msp430GpioC$2$HplGeneralIO$makeOutput(void);
#line 34
static   void /*PlatformLedsC.Led2Impl*/Msp430GpioC$2$HplGeneralIO$set(void);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*PlatformLedsC.Led2Impl*/Msp430GpioC$2$GeneralIO$set(void);





static inline   void /*PlatformLedsC.Led2Impl*/Msp430GpioC$2$GeneralIO$makeOutput(void);
# 68 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
static  error_t Stm25pLogP$Sector$read(
# 45 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a0010, 
# 68 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
stm25p_addr_t arg_0x4079a330, uint8_t *arg_0x4079a4d0, stm25p_len_t arg_0x4079a658);
#line 112
static  error_t Stm25pLogP$Sector$erase(
# 45 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a0010, 
# 112 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
uint8_t arg_0x407b1af8, uint8_t arg_0x407b1c80);
#line 91
static  error_t Stm25pLogP$Sector$write(
# 45 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a0010, 
# 91 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
stm25p_addr_t arg_0x407b3768, uint8_t *arg_0x407b3908, stm25p_len_t arg_0x407b3a90);
#line 56
static  uint8_t Stm25pLogP$Sector$getNumSectors(
# 45 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a0010);
# 75 "/opt/tinyos-2.x/tos/interfaces/LogRead.nc"
static  void Stm25pLogP$Read$readDone(
# 42 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a3868, 
# 75 "/opt/tinyos-2.x/tos/interfaces/LogRead.nc"
void *arg_0x40613668, storage_len_t arg_0x406137f0, error_t arg_0x40613970);
#line 115
static  void Stm25pLogP$Read$seekDone(
# 42 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a3868, 
# 115 "/opt/tinyos-2.x/tos/interfaces/LogRead.nc"
error_t arg_0x40612d00);
# 118 "/opt/tinyos-2.x/tos/interfaces/LogWrite.nc"
static  void Stm25pLogP$Write$syncDone(
# 43 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a2340, 
# 118 "/opt/tinyos-2.x/tos/interfaces/LogWrite.nc"
error_t arg_0x4078fe48);
#line 100
static  void Stm25pLogP$Write$eraseDone(
# 43 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a2340, 
# 100 "/opt/tinyos-2.x/tos/interfaces/LogWrite.nc"
error_t arg_0x4078f480);
#line 68
static  void Stm25pLogP$Write$appendDone(
# 43 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a2340, 
# 68 "/opt/tinyos-2.x/tos/interfaces/LogWrite.nc"
void *arg_0x40790010, storage_len_t arg_0x40790198, bool arg_0x40790320, 
error_t arg_0x407904b0);
# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t Stm25pLogP$ClientResource$release(
# 46 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a8360);
# 78 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t Stm25pLogP$ClientResource$request(
# 46 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
uint8_t arg_0x407a8360);







enum Stm25pLogP$__nesc_unnamed4303 {
  Stm25pLogP$NUM_LOGS = 1U, 
  Stm25pLogP$BLOCK_SIZE = 4096, 
  Stm25pLogP$BLOCK_SIZE_LOG2 = 12, 
  Stm25pLogP$BLOCK_MASK = Stm25pLogP$BLOCK_SIZE - 1, 
  Stm25pLogP$BLOCKS_PER_SECTOR = STM25P_SECTOR_SIZE / Stm25pLogP$BLOCK_SIZE, 
  Stm25pLogP$MAX_RECORD_SIZE = 254, 
  Stm25pLogP$INVALID_HEADER = 0xff
};








#line 64
typedef enum Stm25pLogP$__nesc_unnamed4304 {
  Stm25pLogP$S_IDLE, 
  Stm25pLogP$S_READ, 
  Stm25pLogP$S_SEEK, 
  Stm25pLogP$S_ERASE, 
  Stm25pLogP$S_APPEND, 
  Stm25pLogP$S_SYNC
} Stm25pLogP$stm25p_log_req_t;






#line 73
typedef struct Stm25pLogP$stm25p_log_state_t {
  storage_cookie_t cookie;
  void *buf;
  uint8_t len;
  Stm25pLogP$stm25p_log_req_t req;
} Stm25pLogP$stm25p_log_state_t;





#line 80
typedef struct Stm25pLogP$stm25p_log_info_t {
  stm25p_addr_t read_addr;
  stm25p_addr_t remaining;
  stm25p_addr_t write_addr;
} Stm25pLogP$stm25p_log_info_t;

Stm25pLogP$stm25p_log_state_t Stm25pLogP$m_log_state[Stm25pLogP$NUM_LOGS];
Stm25pLogP$stm25p_log_state_t Stm25pLogP$m_req;
Stm25pLogP$stm25p_log_info_t Stm25pLogP$m_log_info[Stm25pLogP$NUM_LOGS];
stm25p_addr_t Stm25pLogP$m_addr;
bool Stm25pLogP$m_records_lost;
uint8_t Stm25pLogP$m_header;
uint8_t Stm25pLogP$m_len;







#line 94
typedef enum Stm25pLogP$__nesc_unnamed4305 {
  Stm25pLogP$S_SEARCH_BLOCKS, 
  Stm25pLogP$S_SEARCH_RECORDS, 
  Stm25pLogP$S_SEARCH_SEEK, 
  Stm25pLogP$S_HEADER, 
  Stm25pLogP$S_DATA
} Stm25pLogP$stm25p_log_rw_state_t;

Stm25pLogP$stm25p_log_rw_state_t Stm25pLogP$m_rw_state;

static inline error_t Stm25pLogP$newRequest(uint8_t client);
static void Stm25pLogP$continueReadOp(uint8_t client);
static void Stm25pLogP$continueAppendOp(uint8_t client);
static void Stm25pLogP$signalDone(uint8_t id, error_t error);

static inline  error_t Stm25pLogP$Init$init(void);








static inline  error_t Stm25pLogP$Read$read(uint8_t id, void *buf, storage_len_t len);
#line 190
static inline error_t Stm25pLogP$newRequest(uint8_t client);
#line 202
static inline uint8_t Stm25pLogP$calcSector(uint8_t client, stm25p_addr_t addr);




static stm25p_addr_t Stm25pLogP$calcAddr(uint8_t client, stm25p_addr_t addr);






static  void Stm25pLogP$ClientResource$granted(uint8_t id);
#line 270
static void Stm25pLogP$continueReadOp(uint8_t client);
#line 311
static inline  void Stm25pLogP$Sector$readDone(uint8_t id, stm25p_addr_t addr, uint8_t *buf, 
stm25p_len_t len, error_t error);
#line 416
static void Stm25pLogP$continueAppendOp(uint8_t client);
#line 444
static inline  void Stm25pLogP$Sector$eraseDone(uint8_t id, uint8_t sector, 
uint8_t num_sectors, 
error_t error);
#line 468
static inline  void Stm25pLogP$Sector$writeDone(uint8_t id, storage_addr_t addr, 
uint8_t *buf, 
storage_len_t len, 
error_t error);
#line 483
static void Stm25pLogP$signalDone(uint8_t id, error_t error);
#line 512
static inline  void Stm25pLogP$Sector$computeCrcDone(uint8_t id, stm25p_addr_t addr, stm25p_len_t len, uint16_t crc, error_t error);

static inline   void Stm25pLogP$Read$default$readDone(uint8_t id, void *data, storage_len_t len, error_t error);
static inline   void Stm25pLogP$Read$default$seekDone(uint8_t id, error_t error);
static inline   void Stm25pLogP$Write$default$eraseDone(uint8_t id, error_t error);
static inline   void Stm25pLogP$Write$default$appendDone(uint8_t id, void *data, storage_len_t len, bool recordsLost, error_t error);
static inline   void Stm25pLogP$Write$default$syncDone(uint8_t id, error_t error);


static inline   uint8_t Stm25pLogP$Sector$default$getNumSectors(uint8_t id);
static inline   error_t Stm25pLogP$Sector$default$read(uint8_t id, storage_addr_t addr, uint8_t *buf, storage_len_t len);
static inline   error_t Stm25pLogP$Sector$default$write(uint8_t id, storage_addr_t addr, uint8_t *buf, storage_len_t len);
static inline   error_t Stm25pLogP$Sector$default$erase(uint8_t id, uint8_t sector, uint8_t num_sectors);

static inline    error_t Stm25pLogP$ClientResource$default$request(uint8_t id);
static inline    error_t Stm25pLogP$ClientResource$default$release(uint8_t id);
# 92 "/opt/tinyos-2.x/tos/interfaces/SplitControl.nc"
static  void Stm25pSectorP$SplitControl$startDone(error_t arg_0x40620638);
#line 117
static  void Stm25pSectorP$SplitControl$stopDone(error_t arg_0x4061f1e0);
# 101 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
static  void Stm25pSectorP$Sector$writeDone(
# 44 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x4082a958, 
# 101 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
stm25p_addr_t arg_0x407b1088, uint8_t *arg_0x407b1228, stm25p_len_t arg_0x407b13b0, 
error_t arg_0x407b1540);
#line 121
static  void Stm25pSectorP$Sector$eraseDone(
# 44 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x4082a958, 
# 121 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
uint8_t arg_0x407b0250, uint8_t arg_0x407b03d8, error_t arg_0x407b0558);
#line 144
static  void Stm25pSectorP$Sector$computeCrcDone(
# 44 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x4082a958, 
# 144 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
stm25p_addr_t arg_0x407af4e8, stm25p_len_t arg_0x407af670, 
uint16_t arg_0x407af808, error_t arg_0x407af988);
#line 78
static  void Stm25pSectorP$Sector$readDone(
# 44 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x4082a958, 
# 78 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
stm25p_addr_t arg_0x4079ac48, uint8_t *arg_0x4079ade8, stm25p_len_t arg_0x407b3010, 
error_t arg_0x407b31a0);
# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t Stm25pSectorP$Stm25pResource$release(
# 47 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x40828d68);
# 78 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t Stm25pSectorP$Stm25pResource$request(
# 47 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x40828d68);
# 48 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pVolume.nc"
static   volume_id_t Stm25pSectorP$Volume$getVolumeId(
# 45 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x40828778);
# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t Stm25pSectorP$SpiResource$release(void);
#line 78
static   error_t Stm25pSectorP$SpiResource$request(void);
# 47 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpi.nc"
static   error_t Stm25pSectorP$Spi$powerDown(void);
#line 66
static   error_t Stm25pSectorP$Spi$read(stm25p_addr_t arg_0x40821350, uint8_t *arg_0x408214f0, 
stm25p_len_t arg_0x40821688);
#line 136
static   error_t Stm25pSectorP$Spi$sectorErase(uint8_t arg_0x40838010);
#line 55
static   error_t Stm25pSectorP$Spi$powerUp(void);
#line 114
static   error_t Stm25pSectorP$Spi$pageProgram(stm25p_addr_t arg_0x4083ab50, uint8_t *arg_0x4083acf0, 
stm25p_len_t arg_0x4083ae88);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void Stm25pSectorP$ClientResource$granted(
# 43 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
uint8_t arg_0x4082a010);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t Stm25pSectorP$signalDone_task$postTask(void);
# 86 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
enum Stm25pSectorP$__nesc_unnamed4306 {
#line 86
  Stm25pSectorP$signalDone_task = 0U
};
#line 86
typedef int Stm25pSectorP$__nesc_sillytask_signalDone_task[Stm25pSectorP$signalDone_task];
#line 56
enum Stm25pSectorP$__nesc_unnamed4307 {
  Stm25pSectorP$NO_CLIENT = 0xff
};







#line 60
typedef enum Stm25pSectorP$__nesc_unnamed4308 {
  Stm25pSectorP$S_IDLE, 
  Stm25pSectorP$S_READ, 
  Stm25pSectorP$S_WRITE, 
  Stm25pSectorP$S_ERASE, 
  Stm25pSectorP$S_CRC
} Stm25pSectorP$stm25p_sector_state_t;
 Stm25pSectorP$stm25p_sector_state_t Stm25pSectorP$m_state;





#line 69
typedef enum Stm25pSectorP$__nesc_unnamed4309 {
  Stm25pSectorP$S_NONE, 
  Stm25pSectorP$S_START, 
  Stm25pSectorP$S_STOP
} Stm25pSectorP$stm25p_power_state_t;
 Stm25pSectorP$stm25p_power_state_t Stm25pSectorP$m_power_state;

 uint8_t Stm25pSectorP$m_client;
 stm25p_addr_t Stm25pSectorP$m_addr;
 stm25p_len_t Stm25pSectorP$m_len;
 stm25p_len_t Stm25pSectorP$m_cur_len;
 uint8_t *Stm25pSectorP$m_buf;
 error_t Stm25pSectorP$m_error;
 uint16_t Stm25pSectorP$m_crc;


static inline void Stm25pSectorP$signalDone(error_t error);


static  error_t Stm25pSectorP$SplitControl$start(void);






static inline  error_t Stm25pSectorP$SplitControl$stop(void);






static inline   error_t Stm25pSectorP$ClientResource$request(uint8_t id);







static inline   error_t Stm25pSectorP$ClientResource$release(uint8_t id);










static inline  void Stm25pSectorP$Stm25pResource$granted(uint8_t id);




static inline uint8_t Stm25pSectorP$getVolumeId(uint8_t client);



static inline  void Stm25pSectorP$SpiResource$granted(void);
#line 153
static inline stm25p_addr_t Stm25pSectorP$physicalAddr(uint8_t id, stm25p_addr_t addr);




static stm25p_len_t Stm25pSectorP$calcWriteLen(stm25p_addr_t addr);








static  uint8_t Stm25pSectorP$Sector$getNumSectors(uint8_t id);



static  error_t Stm25pSectorP$Sector$read(uint8_t id, stm25p_addr_t addr, uint8_t *buf, 
stm25p_len_t len);










static inline   void Stm25pSectorP$Spi$readDone(stm25p_addr_t addr, uint8_t *buf, 
stm25p_len_t len, error_t error);



static  error_t Stm25pSectorP$Sector$write(uint8_t id, stm25p_addr_t addr, 
uint8_t *buf, 
stm25p_len_t len);
#line 202
static inline   void Stm25pSectorP$Spi$pageProgramDone(stm25p_addr_t addr, uint8_t *buf, 
stm25p_len_t len, error_t error);









static  error_t Stm25pSectorP$Sector$erase(uint8_t id, uint8_t sector, 
uint8_t num_sectors);
#line 226
static inline   void Stm25pSectorP$Spi$sectorEraseDone(uint8_t sector, error_t error);
#line 246
static inline   void Stm25pSectorP$Spi$computeCrcDone(uint16_t crc, stm25p_addr_t addr, 
stm25p_len_t len, error_t error);




static inline   void Stm25pSectorP$Spi$bulkEraseDone(error_t error);



static inline void Stm25pSectorP$signalDone(error_t error);




static inline  void Stm25pSectorP$signalDone_task$runTask(void);
#line 284
static inline   void Stm25pSectorP$ClientResource$default$granted(uint8_t id);
static inline   void Stm25pSectorP$Sector$default$readDone(uint8_t id, stm25p_addr_t addr, uint8_t *buf, stm25p_len_t len, error_t error);
static inline   void Stm25pSectorP$Sector$default$writeDone(uint8_t id, stm25p_addr_t addr, uint8_t *buf, stm25p_len_t len, error_t error);
static inline   void Stm25pSectorP$Sector$default$eraseDone(uint8_t id, uint8_t sector, uint8_t num_sectors, error_t error);
static inline   void Stm25pSectorP$Sector$default$computeCrcDone(uint8_t id, stm25p_addr_t addr, stm25p_len_t len, uint16_t crc, error_t error);
static inline    volume_id_t Stm25pSectorP$Volume$default$getVolumeId(uint8_t id);
# 39 "/opt/tinyos-2.x/tos/system/FcfsResourceQueueC.nc"
enum /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$__nesc_unnamed4310 {
#line 39
  FcfsResourceQueueC$0$NO_ENTRY = 0xFF
};
uint8_t /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$resQ[1U];
uint8_t /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$qHead = /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$NO_ENTRY;
uint8_t /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$qTail = /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$NO_ENTRY;

static inline  error_t /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$Init$init(void);




static inline   bool /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$FcfsQueue$isEmpty(void);



static inline   bool /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$FcfsQueue$isEnqueued(resource_client_id_t id);



static inline   resource_client_id_t /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$FcfsQueue$dequeue(void);
#line 72
static inline   error_t /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$FcfsQueue$enqueue(resource_client_id_t id);
# 43 "/opt/tinyos-2.x/tos/interfaces/ResourceRequested.nc"
static   void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceRequested$requested(
# 55 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089e830);
# 55 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
static   void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceConfigure$unconfigure(
# 60 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089c9b8);
# 49 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
static   void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceConfigure$configure(
# 60 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089c9b8);
# 69 "/opt/tinyos-2.x/tos/interfaces/ResourceQueue.nc"
static   error_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Queue$enqueue(resource_client_id_t arg_0x4088c458);
#line 43
static   bool /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Queue$isEmpty(void);
#line 60
static   resource_client_id_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Queue$dequeue(void);
# 73 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
static   void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceDefaultOwner$requested(void);
#line 46
static   void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceDefaultOwner$granted(void);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Resource$granted(
# 54 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089ff00);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$grantedTask$postTask(void);
# 75 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
enum /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$__nesc_unnamed4311 {
#line 75
  ArbiterP$0$grantedTask = 1U
};
#line 75
typedef int /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$__nesc_sillytask_grantedTask[/*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$grantedTask];
#line 67
enum /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$__nesc_unnamed4312 {
#line 67
  ArbiterP$0$RES_CONTROLLED, ArbiterP$0$RES_GRANTING, ArbiterP$0$RES_IMM_GRANTING, ArbiterP$0$RES_BUSY
};
#line 68
enum /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$__nesc_unnamed4313 {
#line 68
  ArbiterP$0$default_owner_id = 1U
};
#line 69
enum /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$__nesc_unnamed4314 {
#line 69
  ArbiterP$0$NO_RES = 0xFF
};
uint8_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$state = /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$RES_CONTROLLED;
 uint8_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$resId = /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$default_owner_id;
 uint8_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$reqResId;



static inline   error_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Resource$request(uint8_t id);
#line 108
static inline   error_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Resource$release(uint8_t id);
#line 127
static inline   error_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceDefaultOwner$release(void);
#line 182
static inline  void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$grantedTask$runTask(void);
#line 196
static inline    void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceRequested$default$requested(uint8_t id);
#line 208
static inline    void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceConfigure$default$configure(uint8_t id);

static inline    void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceConfigure$default$unconfigure(uint8_t id);
# 30 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Compare$setEvent(uint16_t arg_0x4054f030);

static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Compare$setEventFromNow(uint16_t arg_0x4054f978);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   uint16_t /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Timer$get(void);
# 67 "/opt/tinyos-2.x/tos/lib/timer/Alarm.nc"
static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Alarm$fired(void);
# 39 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerControl.nc"
static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430TimerControl$enableEvents(void);
#line 36
static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430TimerControl$setControlAsCompare(void);



static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430TimerControl$disableEvents(void);
#line 33
static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430TimerControl$clearPendingInterrupt(void);
# 42 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430AlarmC.nc"
static inline  error_t /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Init$init(void);
#line 54
static inline   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Alarm$stop(void);




static inline   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Compare$fired(void);










static inline   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Alarm$startAt(uint16_t t0, uint16_t dt);
#line 103
static inline   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Timer$overflow(void);
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
static   uint16_t /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Msp430Timer$get(void);
static   bool /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Msp430Timer$isOverflowPending(void);
# 71 "/opt/tinyos-2.x/tos/lib/timer/Counter.nc"
static   void /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Counter$overflow(void);
# 38 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430CounterC.nc"
static inline   uint16_t /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Counter$get(void);




static inline   bool /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Counter$isOverflowPending(void);









static inline   void /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Msp430Timer$overflow(void);
# 53 "/opt/tinyos-2.x/tos/lib/timer/Counter.nc"
static   /*CounterMilli32C.Transform*/TransformCounterC$0$CounterFrom$size_type /*CounterMilli32C.Transform*/TransformCounterC$0$CounterFrom$get(void);






static   bool /*CounterMilli32C.Transform*/TransformCounterC$0$CounterFrom$isOverflowPending(void);










static   void /*CounterMilli32C.Transform*/TransformCounterC$0$Counter$overflow(void);
# 56 "/opt/tinyos-2.x/tos/lib/timer/TransformCounterC.nc"
/*CounterMilli32C.Transform*/TransformCounterC$0$upper_count_type /*CounterMilli32C.Transform*/TransformCounterC$0$m_upper;

enum /*CounterMilli32C.Transform*/TransformCounterC$0$__nesc_unnamed4315 {

  TransformCounterC$0$LOW_SHIFT_RIGHT = 5, 
  TransformCounterC$0$HIGH_SHIFT_LEFT = 8 * sizeof(/*CounterMilli32C.Transform*/TransformCounterC$0$from_size_type ) - /*CounterMilli32C.Transform*/TransformCounterC$0$LOW_SHIFT_RIGHT, 
  TransformCounterC$0$NUM_UPPER_BITS = 8 * sizeof(/*CounterMilli32C.Transform*/TransformCounterC$0$to_size_type ) - 8 * sizeof(/*CounterMilli32C.Transform*/TransformCounterC$0$from_size_type ) + 5, 



  TransformCounterC$0$OVERFLOW_MASK = /*CounterMilli32C.Transform*/TransformCounterC$0$NUM_UPPER_BITS ? ((/*CounterMilli32C.Transform*/TransformCounterC$0$upper_count_type )2 << (/*CounterMilli32C.Transform*/TransformCounterC$0$NUM_UPPER_BITS - 1)) - 1 : 0
};

static   /*CounterMilli32C.Transform*/TransformCounterC$0$to_size_type /*CounterMilli32C.Transform*/TransformCounterC$0$Counter$get(void);
#line 122
static inline   void /*CounterMilli32C.Transform*/TransformCounterC$0$CounterFrom$overflow(void);
# 67 "/opt/tinyos-2.x/tos/lib/timer/Alarm.nc"
static   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$fired(void);
#line 92
static   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$AlarmFrom$startAt(/*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$AlarmFrom$size_type arg_0x408f4c30, /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$AlarmFrom$size_type arg_0x408f4db8);
#line 62
static   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$AlarmFrom$stop(void);
# 53 "/opt/tinyos-2.x/tos/lib/timer/Counter.nc"
static   /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Counter$size_type /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Counter$get(void);
# 66 "/opt/tinyos-2.x/tos/lib/timer/TransformAlarmC.nc"
/*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_t0;
/*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_dt;

enum /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$__nesc_unnamed4316 {

  TransformAlarmC$0$MAX_DELAY_LOG2 = 8 * sizeof(/*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$from_size_type ) - 1 - 5, 
  TransformAlarmC$0$MAX_DELAY = (/*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type )1 << /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$MAX_DELAY_LOG2
};

static inline   /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$getNow(void);




static inline   /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$getAlarm(void);










static inline   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$stop(void);




static void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$set_alarm(void);
#line 136
static   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$startAt(/*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type t0, /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type dt);
#line 151
static inline   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$AlarmFrom$fired(void);
#line 166
static inline   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Counter$overflow(void);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$fired$postTask(void);
# 98 "/opt/tinyos-2.x/tos/lib/timer/Alarm.nc"
static   /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$size_type /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$getNow(void);
#line 92
static   void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$startAt(/*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$size_type arg_0x408f4c30, /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$size_type arg_0x408f4db8);
#line 105
static   /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$size_type /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$getAlarm(void);
#line 62
static   void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$stop(void);
# 72 "/opt/tinyos-2.x/tos/lib/timer/Timer.nc"
static  void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Timer$fired(void);
# 63 "/opt/tinyos-2.x/tos/lib/timer/AlarmToTimerC.nc"
enum /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$__nesc_unnamed4317 {
#line 63
  AlarmToTimerC$0$fired = 2U
};
#line 63
typedef int /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$__nesc_sillytask_fired[/*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$fired];
#line 44
uint32_t /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$m_dt;
bool /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$m_oneshot;

static inline void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$start(uint32_t t0, uint32_t dt, bool oneshot);
#line 60
static inline  void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Timer$stop(void);


static inline  void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$fired$runTask(void);






static inline   void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$fired(void);
#line 82
static inline  void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Timer$startOneShotAt(uint32_t t0, uint32_t dt);


static inline  uint32_t /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Timer$getNow(void);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$updateFromTimer$postTask(void);
# 125 "/opt/tinyos-2.x/tos/lib/timer/Timer.nc"
static  uint32_t /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$TimerFrom$getNow(void);
#line 118
static  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$TimerFrom$startOneShotAt(uint32_t arg_0x408d90c0, uint32_t arg_0x408d9248);
#line 67
static  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$TimerFrom$stop(void);




static  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer$fired(
# 37 "/opt/tinyos-2.x/tos/lib/timer/VirtualizeTimerC.nc"
uint8_t arg_0x409ae088);
#line 60
enum /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$__nesc_unnamed4318 {
#line 60
  VirtualizeTimerC$0$updateFromTimer = 3U
};
#line 60
typedef int /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$__nesc_sillytask_updateFromTimer[/*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$updateFromTimer];
#line 42
enum /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$__nesc_unnamed4319 {

  VirtualizeTimerC$0$NUM_TIMERS = 1U, 
  VirtualizeTimerC$0$END_OF_LIST = 255
};








#line 48
typedef struct /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$__nesc_unnamed4320 {

  uint32_t t0;
  uint32_t dt;
  bool isoneshot : 1;
  bool isrunning : 1;
  bool _reserved : 6;
} /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer_t;

/*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer_t /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$m_timers[/*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$NUM_TIMERS];




static void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$fireTimers(uint32_t now);
#line 88
static inline  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$updateFromTimer$runTask(void);
#line 127
static inline  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$TimerFrom$fired(void);




static inline void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$startTimer(uint8_t num, uint32_t t0, uint32_t dt, bool isoneshot);
#line 147
static inline  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer$startOneShot(uint8_t num, uint32_t dt);




static inline  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer$stop(uint8_t num);
#line 192
static inline   void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer$default$fired(uint8_t num);
# 47 "/opt/tinyos-2.x/tos/lib/timer/CounterToLocalTimeC.nc"
static inline   void /*HilTimerMilliC.CounterToLocalTimeC*/CounterToLocalTimeC$0$Counter$overflow(void);
# 83 "/opt/tinyos-2.x/tos/interfaces/SplitControl.nc"
static  error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$SplitControl$start(void);
#line 109
static  error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$SplitControl$stop(void);
# 62 "/opt/tinyos-2.x/tos/lib/timer/Timer.nc"
static  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$TimerMilli$startOneShot(uint32_t arg_0x408dc490);




static  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$TimerMilli$stop(void);
# 52 "/opt/tinyos-2.x/tos/lib/power/PowerDownCleanup.nc"
static   void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$PowerDownCleanup$cleanup(void);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$timerTask$postTask(void);
# 56 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
static   error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$ResourceDefaultOwner$release(void);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$startTask$postTask(void);
# 74 "/opt/tinyos-2.x/tos/interfaces/StdControl.nc"
static  error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$StdControl$start(void);









static  error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$StdControl$stop(void);
# 69 "/opt/tinyos-2.x/tos/lib/power/DeferredPowerManagerP.nc"
enum /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$__nesc_unnamed4321 {
#line 69
  DeferredPowerManagerP$0$startTask = 4U
};
#line 69
typedef int /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$__nesc_sillytask_startTask[/*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$startTask];






enum /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$__nesc_unnamed4322 {
#line 76
  DeferredPowerManagerP$0$timerTask = 5U
};
#line 76
typedef int /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$__nesc_sillytask_timerTask[/*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$timerTask];
#line 65
 bool /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$stopping = FALSE;
 bool /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$requested = FALSE;
 bool /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$stopTimer = FALSE;

static inline  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$startTask$runTask(void);






static inline  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$timerTask$runTask(void);



static inline   void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$ResourceDefaultOwner$requested(void);










static inline   error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$StdControl$default$start(void);







static inline  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$SplitControl$startDone(error_t error);



static inline   void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$ResourceDefaultOwner$granted(void);



static inline  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$TimerMilli$fired(void);








static inline  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$SplitControl$stopDone(error_t error);










static inline   error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$StdControl$default$stop(void);







static inline    void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$PowerDownCleanup$default$cleanup(void);
# 59 "/opt/tinyos-2.x/tos/interfaces/SpiPacket.nc"
static   error_t Stm25pSpiP$SpiPacket$send(uint8_t *arg_0x40a0e320, uint8_t *arg_0x40a0e4c0, uint16_t arg_0x40a0e648);
# 34 "/opt/tinyos-2.x/tos/interfaces/SpiByte.nc"
static   uint8_t Stm25pSpiP$SpiByte$write(uint8_t arg_0x40a12a18);
# 35 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
static   void Stm25pSpiP$CSN$makeOutput(void);
#line 29
static   void Stm25pSpiP$CSN$set(void);
static   void Stm25pSpiP$CSN$clr(void);
# 144 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpi.nc"
static   void Stm25pSpiP$Spi$sectorEraseDone(uint8_t arg_0x408385b8, error_t arg_0x40838738);
#line 77
static   void Stm25pSpiP$Spi$readDone(stm25p_addr_t arg_0x40821c90, uint8_t *arg_0x40821e30, 
stm25p_len_t arg_0x4081f010, error_t arg_0x4081f190);
#line 125
static   void Stm25pSpiP$Spi$pageProgramDone(stm25p_addr_t arg_0x408394d8, uint8_t *arg_0x40839678, 
stm25p_len_t arg_0x40839810, error_t arg_0x40839990);
#line 101
static   void Stm25pSpiP$Spi$computeCrcDone(uint16_t arg_0x4083a0b0, stm25p_addr_t arg_0x4083a238, 
stm25p_len_t arg_0x4083a3d0, error_t arg_0x4083a550);
#line 159
static   void Stm25pSpiP$Spi$bulkEraseDone(error_t arg_0x408360b8);
# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t Stm25pSpiP$SpiResource$release(void);
#line 78
static   error_t Stm25pSpiP$SpiResource$request(void);
#line 92
static  void Stm25pSpiP$ClientResource$granted(void);
# 35 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
static   void Stm25pSpiP$Hold$makeOutput(void);
#line 29
static   void Stm25pSpiP$Hold$set(void);
# 56 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpiP.nc"
enum Stm25pSpiP$__nesc_unnamed4323 {
  Stm25pSpiP$CRC_BUF_SIZE = 16
};









#line 60
typedef enum Stm25pSpiP$__nesc_unnamed4324 {
  Stm25pSpiP$S_READ = 0x3, 
  Stm25pSpiP$S_PAGE_PROGRAM = 0x2, 
  Stm25pSpiP$S_SECTOR_ERASE = 0xd8, 
  Stm25pSpiP$S_BULK_ERASE = 0xc7, 
  Stm25pSpiP$S_WRITE_ENABLE = 0x6, 
  Stm25pSpiP$S_POWER_ON = 0xab, 
  Stm25pSpiP$S_DEEP_SLEEP = 0xb9
} Stm25pSpiP$stm25p_cmd_t;

 uint8_t Stm25pSpiP$m_cmd[4];

 bool Stm25pSpiP$m_is_writing = FALSE;
 bool Stm25pSpiP$m_computing_crc = FALSE;

 stm25p_addr_t Stm25pSpiP$m_addr;
 uint8_t *Stm25pSpiP$m_buf;
 stm25p_len_t Stm25pSpiP$m_len;
 stm25p_addr_t Stm25pSpiP$m_cur_addr;
 stm25p_len_t Stm25pSpiP$m_cur_len;
 uint8_t Stm25pSpiP$m_crc_buf[Stm25pSpiP$CRC_BUF_SIZE];
 uint16_t Stm25pSpiP$m_crc;

static error_t Stm25pSpiP$newRequest(bool write, stm25p_len_t cmd_len);
static void Stm25pSpiP$signalDone(error_t error);

static uint8_t Stm25pSpiP$sendCmd(uint8_t cmd, uint8_t len);
#line 100
static inline  error_t Stm25pSpiP$Init$init(void);







static inline   error_t Stm25pSpiP$ClientResource$request(void);







static inline   error_t Stm25pSpiP$ClientResource$release(void);







static inline stm25p_len_t Stm25pSpiP$calcReadLen(void);



static inline   error_t Stm25pSpiP$Spi$powerDown(void);




static inline   error_t Stm25pSpiP$Spi$powerUp(void);




static inline   error_t Stm25pSpiP$Spi$read(stm25p_addr_t addr, uint8_t *buf, 
stm25p_len_t len);
#line 156
static   error_t Stm25pSpiP$Spi$pageProgram(stm25p_addr_t addr, uint8_t *buf, 
stm25p_len_t len);







static   error_t Stm25pSpiP$Spi$sectorErase(uint8_t sector);










static error_t Stm25pSpiP$newRequest(bool write, stm25p_len_t cmd_len);










static void Stm25pSpiP$releaseAndRequest(void);




static   void Stm25pSpiP$SpiPacket$sendDone(uint8_t *tx_buf, uint8_t *rx_buf, 
uint16_t len, error_t error);
#line 238
static inline  void Stm25pSpiP$SpiResource$granted(void);










static void Stm25pSpiP$signalDone(error_t error);
# 71 "/opt/tinyos-2.x/tos/interfaces/SpiPacket.nc"
static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$SpiPacket$sendDone(
# 43 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
uint8_t arg_0x40a7d790, 
# 71 "/opt/tinyos-2.x/tos/interfaces/SpiPacket.nc"
uint8_t *arg_0x40a0ed80, uint8_t *arg_0x40a0ef20, uint16_t arg_0x40a0d0c0, 
error_t arg_0x40a0d250);
# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiConfigure.nc"
static   msp430_spi_union_config_t */*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Msp430SpiConfigure$getConfig(
# 46 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
uint8_t arg_0x40a7c850);
# 180 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$enableRxIntr(void);
#line 197
static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$clrRxIntr(void);
#line 97
static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$resetUsart(bool arg_0x40a98a30);
#line 177
static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$disableRxIntr(void);









static   bool /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$isTxIntrPending(void);
#line 224
static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$tx(uint8_t arg_0x40a8fc18);
#line 168
static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$setModeSpi(msp430_spi_union_config_t *arg_0x40a94aa0);
#line 231
static   uint8_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$rx(void);
#line 192
static   bool /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$isRxIntrPending(void);









static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$clrTxIntr(void);
#line 158
static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$disableSpi(void);
# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$release(
# 45 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
uint8_t arg_0x40a7de58);
# 78 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$request(
# 45 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
uint8_t arg_0x40a7de58);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Resource$granted(
# 40 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
uint8_t arg_0x40a7e488);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$signalDone_task$postTask(void);
# 66 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
enum /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$__nesc_unnamed4325 {
#line 66
  Msp430SpiNoDmaP$0$signalDone_task = 6U
};
#line 66
typedef int /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$__nesc_sillytask_signalDone_task[/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$signalDone_task];
#line 55
enum /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$__nesc_unnamed4326 {
  Msp430SpiNoDmaP$0$SPI_ATOMIC_SIZE = 2
};

 uint8_t */*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_tx_buf;
 uint8_t */*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_rx_buf;
 uint16_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_len;
 uint16_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_pos;
 uint8_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_client;

static inline void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$signalDone(void);






static inline   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Resource$request(uint8_t id);







static inline   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Resource$release(uint8_t id);



static inline   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$ResourceConfigure$configure(uint8_t id);



static inline   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$ResourceConfigure$unconfigure(uint8_t id);





static inline  void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$granted(uint8_t id);



static inline   uint8_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$SpiByte$write(uint8_t tx);
#line 111
static inline    error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$default$request(uint8_t id);

static inline    error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$default$release(uint8_t id);
static inline    msp430_spi_union_config_t */*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Msp430SpiConfigure$default$getConfig(uint8_t id);



static inline   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Resource$default$granted(uint8_t id);

static void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$continueOp(void);
#line 146
static   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$SpiPacket$send(uint8_t id, uint8_t *tx_buf, 
uint8_t *rx_buf, 
uint16_t len);
#line 168
static inline  void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$signalDone_task$runTask(void);



static inline   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartInterrupts$rxDone(uint8_t data);
#line 185
static inline void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$signalDone(void);




static inline   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartInterrupts$txDone(void);

static inline    void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$SpiPacket$default$sendDone(uint8_t id, uint8_t *tx_buf, uint8_t *rx_buf, uint16_t len, error_t error);
# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
static   void HplMsp430Usart0P$UCLK$selectIOFunc(void);
#line 78
static   void HplMsp430Usart0P$UCLK$selectModuleFunc(void);
# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
static   void HplMsp430Usart0P$Interrupts$rxDone(uint8_t arg_0x40a82ae8);
#line 49
static   void HplMsp430Usart0P$Interrupts$txDone(void);
# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
static   void HplMsp430Usart0P$URXD$selectIOFunc(void);
#line 85
static   void HplMsp430Usart0P$UTXD$selectIOFunc(void);
# 7 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430I2C.nc"
static   void HplMsp430Usart0P$HplI2C$clearModeI2C(void);
#line 6
static   bool HplMsp430Usart0P$HplI2C$isI2C(void);
# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
static   void HplMsp430Usart0P$SOMI$selectIOFunc(void);
#line 78
static   void HplMsp430Usart0P$SOMI$selectModuleFunc(void);
# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430I2CInterrupts.nc"
static   void HplMsp430Usart0P$I2CInterrupts$fired(void);
# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
static   void HplMsp430Usart0P$SIMO$selectIOFunc(void);
#line 78
static   void HplMsp430Usart0P$SIMO$selectModuleFunc(void);
# 89 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart0P.nc"
 static volatile uint8_t HplMsp430Usart0P$IE1 __asm ("0x0000");
 static volatile uint8_t HplMsp430Usart0P$ME1 __asm ("0x0004");
 static volatile uint8_t HplMsp430Usart0P$IFG1 __asm ("0x0002");
 static volatile uint8_t HplMsp430Usart0P$U0TCTL __asm ("0x0071");

 static volatile uint8_t HplMsp430Usart0P$U0TXBUF __asm ("0x0077");

void sig_UART0RX_VECTOR(void) __attribute((wakeup)) __attribute((interrupt(18)))  ;




void sig_UART0TX_VECTOR(void) __attribute((wakeup)) __attribute((interrupt(16)))  ;
#line 132
static inline   void HplMsp430Usart0P$Usart$setUbr(uint16_t control);










static inline   void HplMsp430Usart0P$Usart$setUmctl(uint8_t control);







static inline   void HplMsp430Usart0P$Usart$resetUsart(bool reset);
#line 207
static inline   void HplMsp430Usart0P$Usart$disableUart(void);
#line 238
static inline   void HplMsp430Usart0P$Usart$enableSpi(void);








static inline   void HplMsp430Usart0P$Usart$disableSpi(void);








static inline void HplMsp430Usart0P$configSpi(msp430_spi_union_config_t *config);








static inline   void HplMsp430Usart0P$Usart$setModeSpi(msp430_spi_union_config_t *config);
#line 316
static inline   bool HplMsp430Usart0P$Usart$isTxIntrPending(void);
#line 330
static inline   bool HplMsp430Usart0P$Usart$isRxIntrPending(void);






static inline   void HplMsp430Usart0P$Usart$clrTxIntr(void);



static inline   void HplMsp430Usart0P$Usart$clrRxIntr(void);



static inline   void HplMsp430Usart0P$Usart$clrIntr(void);



static inline   void HplMsp430Usart0P$Usart$disableRxIntr(void);







static inline   void HplMsp430Usart0P$Usart$disableIntr(void);



static inline   void HplMsp430Usart0P$Usart$enableRxIntr(void);
#line 382
static inline   void HplMsp430Usart0P$Usart$tx(uint8_t data);



static   uint8_t HplMsp430Usart0P$Usart$rx(void);
# 80 "/opt/tinyos-2.x/tos/interfaces/ArbiterInfo.nc"
static   bool /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$ArbiterInfo$inUse(void);







static   uint8_t /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$ArbiterInfo$userId(void);
# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
static   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$Interrupts$rxDone(
# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
uint8_t arg_0x40b38d48, 
# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
uint8_t arg_0x40a82ae8);
#line 49
static   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$Interrupts$txDone(
# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
uint8_t arg_0x40b38d48);
# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430I2CInterrupts.nc"
static   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$I2CInterrupts$fired(
# 40 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
uint8_t arg_0x40b37468);








static inline   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$RawInterrupts$txDone(void);




static inline   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$RawInterrupts$rxDone(uint8_t data);




static inline   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$RawI2CInterrupts$fired(void);




static inline    void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$Interrupts$default$txDone(uint8_t id);
static inline    void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$Interrupts$default$rxDone(uint8_t id, uint8_t data);
static inline    void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$I2CInterrupts$default$fired(uint8_t id);
# 39 "/opt/tinyos-2.x/tos/system/FcfsResourceQueueC.nc"
enum /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$__nesc_unnamed4327 {
#line 39
  FcfsResourceQueueC$1$NO_ENTRY = 0xFF
};
uint8_t /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$resQ[1U];
uint8_t /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$qHead = /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$NO_ENTRY;
uint8_t /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$qTail = /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$NO_ENTRY;

static inline  error_t /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$Init$init(void);




static inline   bool /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$FcfsQueue$isEmpty(void);



static inline   bool /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$FcfsQueue$isEnqueued(resource_client_id_t id);



static inline   resource_client_id_t /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$FcfsQueue$dequeue(void);
#line 72
static inline   error_t /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$FcfsQueue$enqueue(resource_client_id_t id);
# 43 "/opt/tinyos-2.x/tos/interfaces/ResourceRequested.nc"
static   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceRequested$requested(
# 55 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089e830);
# 55 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
static   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceConfigure$unconfigure(
# 60 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089c9b8);
# 49 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
static   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceConfigure$configure(
# 60 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089c9b8);
# 69 "/opt/tinyos-2.x/tos/interfaces/ResourceQueue.nc"
static   error_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Queue$enqueue(resource_client_id_t arg_0x4088c458);
#line 43
static   bool /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Queue$isEmpty(void);
#line 60
static   resource_client_id_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Queue$dequeue(void);
# 73 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
static   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$requested(void);
#line 46
static   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$granted(void);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Resource$granted(
# 54 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089ff00);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$grantedTask$postTask(void);
# 75 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
enum /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$__nesc_unnamed4328 {
#line 75
  ArbiterP$1$grantedTask = 7U
};
#line 75
typedef int /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$__nesc_sillytask_grantedTask[/*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$grantedTask];
#line 67
enum /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$__nesc_unnamed4329 {
#line 67
  ArbiterP$1$RES_CONTROLLED, ArbiterP$1$RES_GRANTING, ArbiterP$1$RES_IMM_GRANTING, ArbiterP$1$RES_BUSY
};
#line 68
enum /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$__nesc_unnamed4330 {
#line 68
  ArbiterP$1$default_owner_id = 1U
};
#line 69
enum /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$__nesc_unnamed4331 {
#line 69
  ArbiterP$1$NO_RES = 0xFF
};
uint8_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$state = /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$RES_CONTROLLED;
 uint8_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$resId = /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$default_owner_id;
 uint8_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$reqResId;



static   error_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Resource$request(uint8_t id);
#line 108
static   error_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Resource$release(uint8_t id);
#line 127
static inline   error_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$release(void);
#line 147
static   bool /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ArbiterInfo$inUse(void);
#line 160
static   uint8_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ArbiterInfo$userId(void);
#line 182
static inline  void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$grantedTask$runTask(void);
#line 194
static inline   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Resource$default$granted(uint8_t id);

static inline    void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceRequested$default$requested(uint8_t id);



static inline    void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$default$granted(void);

static inline    void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$default$requested(void);





static inline    void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceConfigure$default$configure(uint8_t id);

static inline    void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceConfigure$default$unconfigure(uint8_t id);
# 97 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
static   void HplMsp430I2C0P$HplUsart$resetUsart(bool arg_0x40a98a30);
# 49 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430I2C0P.nc"
 static volatile uint8_t HplMsp430I2C0P$U0CTL __asm ("0x0070");





static inline   bool HplMsp430I2C0P$HplI2C$isI2C(void);



static inline   void HplMsp430I2C0P$HplI2C$clearModeI2C(void);
# 71 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
static   void /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$HplGeneralIO$makeOutput(void);
#line 34
static   void /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$HplGeneralIO$set(void);




static   void /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$HplGeneralIO$clr(void);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$GeneralIO$set(void);
static inline   void /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$GeneralIO$clr(void);




static inline   void /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$GeneralIO$makeOutput(void);
# 71 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
static   void /*HplStm25pPinsC.HoldM*/Msp430GpioC$4$HplGeneralIO$makeOutput(void);
#line 34
static   void /*HplStm25pPinsC.HoldM*/Msp430GpioC$4$HplGeneralIO$set(void);
# 37 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*HplStm25pPinsC.HoldM*/Msp430GpioC$4$GeneralIO$set(void);





static inline   void /*HplStm25pPinsC.HoldM*/Msp430GpioC$4$GeneralIO$makeOutput(void);
# 45 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pBinderP.nc"
static inline   volume_id_t /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC.BinderP*/Stm25pBinderP$0$Volume$getVolumeId(void);
# 64 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
static  error_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$SubSend$send(message_t *arg_0x40bf8a90, uint8_t arg_0x40bf8c10);
# 99 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
static  void /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMSend$sendDone(
# 36 "/opt/tinyos-2.x/tos/lib/serial/SerialActiveMessageP.nc"
am_id_t arg_0x40c00c98, 
# 99 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
message_t *arg_0x40636ea0, error_t arg_0x40635030);
# 67 "/opt/tinyos-2.x/tos/interfaces/Receive.nc"
static  message_t */*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Receive$receive(
# 37 "/opt/tinyos-2.x/tos/lib/serial/SerialActiveMessageP.nc"
am_id_t arg_0x40bff650, 
# 67 "/opt/tinyos-2.x/tos/interfaces/Receive.nc"
message_t *arg_0x406437d0, void *arg_0x40643968, uint8_t arg_0x40643ae8);
# 49 "/opt/tinyos-2.x/tos/lib/serial/SerialActiveMessageP.nc"
static inline serial_header_t */*SerialActiveMessageC.AM*/SerialActiveMessageP$0$getHeader(message_t *msg);



static  error_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMSend$send(am_id_t id, am_addr_t dest, 
message_t *msg, 
uint8_t len);
#line 81
static inline  void /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$SubSend$sendDone(message_t *msg, error_t result);







static inline   message_t */*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Receive$default$receive(uint8_t id, message_t *msg, void *payload, uint8_t len);



static inline  message_t */*SerialActiveMessageC.AM*/SerialActiveMessageP$0$SubReceive$receive(message_t *msg, void *payload, uint8_t len);







static inline  uint8_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Packet$payloadLength(message_t *msg);




static inline  void /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Packet$setPayloadLength(message_t *msg, uint8_t len);



static inline  uint8_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Packet$maxPayloadLength(void);



static inline  void */*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Packet$getPayload(message_t *msg, uint8_t len);
#line 127
static  am_addr_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$destination(message_t *amsg);









static inline  void /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$setDestination(message_t *amsg, am_addr_t addr);
#line 151
static  am_id_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$type(message_t *amsg);




static  void /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$setType(message_t *amsg, am_id_t type);
# 92 "/opt/tinyos-2.x/tos/interfaces/SplitControl.nc"
static  void SerialP$SplitControl$startDone(error_t arg_0x40620638);
#line 117
static  void SerialP$SplitControl$stopDone(error_t arg_0x4061f1e0);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t SerialP$stopDoneTask$postTask(void);
# 74 "/opt/tinyos-2.x/tos/interfaces/StdControl.nc"
static  error_t SerialP$SerialControl$start(void);









static  error_t SerialP$SerialControl$stop(void);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t SerialP$RunTx$postTask(void);
# 38 "/opt/tinyos-2.x/tos/lib/serial/SerialFlush.nc"
static  void SerialP$SerialFlush$flush(void);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t SerialP$startDoneTask$postTask(void);
# 45 "/opt/tinyos-2.x/tos/lib/serial/SerialFrameComm.nc"
static   error_t SerialP$SerialFrameComm$putDelimiter(void);
#line 68
static   void SerialP$SerialFrameComm$resetReceive(void);
#line 54
static   error_t SerialP$SerialFrameComm$putData(uint8_t arg_0x40c30e60);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t SerialP$defaultSerialFlushTask$postTask(void);
# 70 "/opt/tinyos-2.x/tos/lib/serial/SendBytePacket.nc"
static   uint8_t SerialP$SendBytePacket$nextByte(void);









static   void SerialP$SendBytePacket$sendCompleted(error_t arg_0x40c38650);
# 51 "/opt/tinyos-2.x/tos/lib/serial/ReceiveBytePacket.nc"
static   error_t SerialP$ReceiveBytePacket$startPacket(void);






static   void SerialP$ReceiveBytePacket$byteReceived(uint8_t arg_0x40c347c0);










static   void SerialP$ReceiveBytePacket$endPacket(error_t arg_0x40c34d80);
# 189 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
enum SerialP$__nesc_unnamed4332 {
#line 189
  SerialP$RunTx = 8U
};
#line 189
typedef int SerialP$__nesc_sillytask_RunTx[SerialP$RunTx];
#line 320
enum SerialP$__nesc_unnamed4333 {
#line 320
  SerialP$startDoneTask = 9U
};
#line 320
typedef int SerialP$__nesc_sillytask_startDoneTask[SerialP$startDoneTask];





enum SerialP$__nesc_unnamed4334 {
#line 326
  SerialP$stopDoneTask = 10U
};
#line 326
typedef int SerialP$__nesc_sillytask_stopDoneTask[SerialP$stopDoneTask];








enum SerialP$__nesc_unnamed4335 {
#line 335
  SerialP$defaultSerialFlushTask = 11U
};
#line 335
typedef int SerialP$__nesc_sillytask_defaultSerialFlushTask[SerialP$defaultSerialFlushTask];
#line 79
enum SerialP$__nesc_unnamed4336 {
  SerialP$RX_DATA_BUFFER_SIZE = 2, 
  SerialP$TX_DATA_BUFFER_SIZE = 4, 
  SerialP$SERIAL_MTU = 255, 
  SerialP$SERIAL_VERSION = 1, 
  SerialP$ACK_QUEUE_SIZE = 5
};

enum SerialP$__nesc_unnamed4337 {
  SerialP$RXSTATE_NOSYNC, 
  SerialP$RXSTATE_PROTO, 
  SerialP$RXSTATE_TOKEN, 
  SerialP$RXSTATE_INFO, 
  SerialP$RXSTATE_INACTIVE
};

enum SerialP$__nesc_unnamed4338 {
  SerialP$TXSTATE_IDLE, 
  SerialP$TXSTATE_PROTO, 
  SerialP$TXSTATE_SEQNO, 
  SerialP$TXSTATE_INFO, 
  SerialP$TXSTATE_FCS1, 
  SerialP$TXSTATE_FCS2, 
  SerialP$TXSTATE_ENDFLAG, 
  SerialP$TXSTATE_ENDWAIT, 
  SerialP$TXSTATE_FINISH, 
  SerialP$TXSTATE_ERROR, 
  SerialP$TXSTATE_INACTIVE
};





#line 109
typedef enum SerialP$__nesc_unnamed4339 {
  SerialP$BUFFER_AVAILABLE, 
  SerialP$BUFFER_FILLING, 
  SerialP$BUFFER_COMPLETE
} SerialP$tx_data_buffer_states_t;

enum SerialP$__nesc_unnamed4340 {
  SerialP$TX_ACK_INDEX = 0, 
  SerialP$TX_DATA_INDEX = 1, 
  SerialP$TX_BUFFER_COUNT = 2
};






#line 122
typedef struct SerialP$__nesc_unnamed4341 {
  uint8_t writePtr;
  uint8_t readPtr;
  uint8_t buf[SerialP$RX_DATA_BUFFER_SIZE + 1];
} SerialP$rx_buf_t;




#line 128
typedef struct SerialP$__nesc_unnamed4342 {
  uint8_t state;
  uint8_t buf;
} SerialP$tx_buf_t;





#line 133
typedef struct SerialP$__nesc_unnamed4343 {
  uint8_t writePtr;
  uint8_t readPtr;
  uint8_t buf[SerialP$ACK_QUEUE_SIZE + 1];
} SerialP$ack_queue_t;



SerialP$rx_buf_t SerialP$rxBuf;
SerialP$tx_buf_t SerialP$txBuf[SerialP$TX_BUFFER_COUNT];



uint8_t SerialP$rxState;
uint8_t SerialP$rxByteCnt;
uint8_t SerialP$rxProto;
uint8_t SerialP$rxSeqno;
uint16_t SerialP$rxCRC;



 uint8_t SerialP$txState;
 uint8_t SerialP$txByteCnt;
 uint8_t SerialP$txProto;
 uint8_t SerialP$txSeqno;
 uint16_t SerialP$txCRC;
uint8_t SerialP$txPending;
 uint8_t SerialP$txIndex;


SerialP$ack_queue_t SerialP$ackQ;

bool SerialP$offPending = FALSE;



static __inline void SerialP$txInit(void);
static __inline void SerialP$rxInit(void);
static __inline void SerialP$ackInit(void);

static __inline bool SerialP$ack_queue_is_full(void);
static __inline bool SerialP$ack_queue_is_empty(void);
static __inline void SerialP$ack_queue_push(uint8_t token);
static __inline uint8_t SerialP$ack_queue_top(void);
static inline uint8_t SerialP$ack_queue_pop(void);




static __inline void SerialP$rx_buffer_push(uint8_t data);
static __inline uint8_t SerialP$rx_buffer_top(void);
static __inline uint8_t SerialP$rx_buffer_pop(void);
static __inline uint16_t SerialP$rx_current_crc(void);

static void SerialP$rx_state_machine(bool isDelimeter, uint8_t data);
static void SerialP$MaybeScheduleTx(void);




static __inline void SerialP$txInit(void);
#line 205
static __inline void SerialP$rxInit(void);








static __inline void SerialP$ackInit(void);



static inline  error_t SerialP$Init$init(void);
#line 232
static __inline bool SerialP$ack_queue_is_full(void);









static __inline bool SerialP$ack_queue_is_empty(void);





static __inline void SerialP$ack_queue_push(uint8_t token);









static __inline uint8_t SerialP$ack_queue_top(void);









static inline uint8_t SerialP$ack_queue_pop(void);
#line 295
static __inline void SerialP$rx_buffer_push(uint8_t data);



static __inline uint8_t SerialP$rx_buffer_top(void);



static __inline uint8_t SerialP$rx_buffer_pop(void);





static __inline uint16_t SerialP$rx_current_crc(void);










static inline  void SerialP$startDoneTask$runTask(void);





static inline  void SerialP$stopDoneTask$runTask(void);



static inline  void SerialP$SerialFlush$flushDone(void);




static inline  void SerialP$defaultSerialFlushTask$runTask(void);


static inline   void SerialP$SerialFlush$default$flush(void);



static inline  error_t SerialP$SplitControl$start(void);




static void SerialP$testOff(void);
#line 384
static inline   void SerialP$SerialFrameComm$delimiterReceived(void);


static inline   void SerialP$SerialFrameComm$dataReceived(uint8_t data);



static inline bool SerialP$valid_rx_proto(uint8_t proto);










static void SerialP$rx_state_machine(bool isDelimeter, uint8_t data);
#line 502
static void SerialP$MaybeScheduleTx(void);










static inline   error_t SerialP$SendBytePacket$completeSend(void);








static inline   error_t SerialP$SendBytePacket$startSend(uint8_t b);
#line 539
static inline  void SerialP$RunTx$runTask(void);
#line 642
static inline   void SerialP$SerialFrameComm$putDone(void);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTask$postTask(void);
# 89 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
static  void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Send$sendDone(
# 40 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
uart_id_t arg_0x40cafa48, 
# 89 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
message_t *arg_0x40bf6dd8, error_t arg_0x40bf5010);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$signalSendDone$postTask(void);
# 67 "/opt/tinyos-2.x/tos/interfaces/Receive.nc"
static  message_t */*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Receive$receive(
# 39 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
uart_id_t arg_0x40caf4a8, 
# 67 "/opt/tinyos-2.x/tos/interfaces/Receive.nc"
message_t *arg_0x406437d0, void *arg_0x40643968, uint8_t arg_0x40643ae8);
# 31 "/opt/tinyos-2.x/tos/lib/serial/SerialPacketInfo.nc"
static   uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$upperLength(
# 43 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
uart_id_t arg_0x40cae450, 
# 31 "/opt/tinyos-2.x/tos/lib/serial/SerialPacketInfo.nc"
message_t *arg_0x40c197f0, uint8_t arg_0x40c19978);
#line 15
static   uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$offset(
# 43 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
uart_id_t arg_0x40cae450);
# 23 "/opt/tinyos-2.x/tos/lib/serial/SerialPacketInfo.nc"
static   uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$dataLinkLength(
# 43 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
uart_id_t arg_0x40cae450, 
# 23 "/opt/tinyos-2.x/tos/lib/serial/SerialPacketInfo.nc"
message_t *arg_0x40c19010, uint8_t arg_0x40c19198);
# 60 "/opt/tinyos-2.x/tos/lib/serial/SendBytePacket.nc"
static   error_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SendBytePacket$completeSend(void);
#line 51
static   error_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SendBytePacket$startSend(uint8_t arg_0x40c3a658);
# 147 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
enum /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$__nesc_unnamed4344 {
#line 147
  SerialDispatcherP$0$signalSendDone = 12U
};
#line 147
typedef int /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$__nesc_sillytask_signalSendDone[/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$signalSendDone];
#line 264
enum /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$__nesc_unnamed4345 {
#line 264
  SerialDispatcherP$0$receiveTask = 13U
};
#line 264
typedef int /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$__nesc_sillytask_receiveTask[/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTask];
#line 55
#line 51
typedef enum /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$__nesc_unnamed4346 {
  SerialDispatcherP$0$SEND_STATE_IDLE = 0, 
  SerialDispatcherP$0$SEND_STATE_BEGIN = 1, 
  SerialDispatcherP$0$SEND_STATE_DATA = 2
} /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$send_state_t;

enum /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$__nesc_unnamed4347 {
  SerialDispatcherP$0$RECV_STATE_IDLE = 0, 
  SerialDispatcherP$0$RECV_STATE_BEGIN = 1, 
  SerialDispatcherP$0$RECV_STATE_DATA = 2
};






#line 63
typedef struct /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$__nesc_unnamed4348 {
  uint8_t which : 1;
  uint8_t bufZeroLocked : 1;
  uint8_t bufOneLocked : 1;
  uint8_t state : 2;
} /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$recv_state_t;



/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$recv_state_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState = { 0, 0, 0, /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$RECV_STATE_IDLE };
uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$recvType = TOS_SERIAL_UNKNOWN_ID;
uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$recvIndex = 0;


message_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$messages[2];
message_t */*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$messagePtrs[2] = { &/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$messages[0], &/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$messages[1] };




uint8_t */*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveBuffer = (uint8_t *)&/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$messages[0];

uint8_t */*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendBuffer = (void *)0;
/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$send_state_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendState = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SEND_STATE_IDLE;
uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendLen = 0;
uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendIndex = 0;
 error_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendError = SUCCESS;
bool /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendCancelled = FALSE;
uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendId = 0;


uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTaskPending = FALSE;
uart_id_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTaskType = 0;
uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTaskWhich;
message_t */*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTaskBuf = (void *)0;
uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTaskSize = 0;

static inline  error_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Send$send(uint8_t id, message_t *msg, uint8_t len);
#line 147
static inline  void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$signalSendDone$runTask(void);
#line 167
static inline   uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SendBytePacket$nextByte(void);
#line 183
static inline   void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SendBytePacket$sendCompleted(error_t error);




static inline bool /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$isCurrentBufferLocked(void);



static inline void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$lockCurrentBuffer(void);








static inline void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$unlockBuffer(uint8_t which);








static inline void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveBufferSwap(void);




static inline   error_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$ReceiveBytePacket$startPacket(void);
#line 233
static inline   void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$ReceiveBytePacket$byteReceived(uint8_t b);
#line 264
static inline  void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTask$runTask(void);
#line 285
static   void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$ReceiveBytePacket$endPacket(error_t result);
#line 344
static inline    uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$default$offset(uart_id_t id);


static inline    uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$default$dataLinkLength(uart_id_t id, message_t *msg, 
uint8_t upperLen);


static inline    uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$default$upperLength(uart_id_t id, message_t *msg, 
uint8_t dataLinkLen);




static inline   message_t */*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Receive$default$receive(uart_id_t idxxx, message_t *msg, 
void *payload, 
uint8_t len);


static inline   void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Send$default$sendDone(uart_id_t idxxx, message_t *msg, error_t error);
# 48 "/opt/tinyos-2.x/tos/interfaces/UartStream.nc"
static   error_t HdlcTranslateC$UartStream$send(uint8_t *arg_0x40ccee50, uint16_t arg_0x40ccd010);
# 83 "/opt/tinyos-2.x/tos/lib/serial/SerialFrameComm.nc"
static   void HdlcTranslateC$SerialFrameComm$dataReceived(uint8_t arg_0x40c2e0f8);





static   void HdlcTranslateC$SerialFrameComm$putDone(void);
#line 74
static   void HdlcTranslateC$SerialFrameComm$delimiterReceived(void);
# 47 "/opt/tinyos-2.x/tos/lib/serial/HdlcTranslateC.nc"
#line 44
typedef struct HdlcTranslateC$__nesc_unnamed4349 {
  uint8_t sendEscape : 1;
  uint8_t receiveEscape : 1;
} HdlcTranslateC$HdlcState;


 HdlcTranslateC$HdlcState HdlcTranslateC$state = { 0, 0 };
 uint8_t HdlcTranslateC$txTemp;
 uint8_t HdlcTranslateC$m_data;


static inline   void HdlcTranslateC$SerialFrameComm$resetReceive(void);





static inline   void HdlcTranslateC$UartStream$receivedByte(uint8_t data);
#line 86
static   error_t HdlcTranslateC$SerialFrameComm$putDelimiter(void);





static   error_t HdlcTranslateC$SerialFrameComm$putData(uint8_t data);
#line 104
static inline   void HdlcTranslateC$UartStream$sendDone(uint8_t *buf, uint16_t len, 
error_t error);










static inline   void HdlcTranslateC$UartStream$receiveDone(uint8_t *buf, uint16_t len, error_t error);
# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartConfigure.nc"
static   msp430_uart_union_config_t */*Msp430Uart1P.UartP*/Msp430UartP$0$Msp430UartConfigure$getConfig(
# 49 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
uint8_t arg_0x40d26d48);
# 97 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$resetUsart(bool arg_0x40a98a30);
#line 179
static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$disableIntr(void);


static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$enableIntr(void);
#line 224
static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$tx(uint8_t arg_0x40a8fc18);
#line 128
static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$disableUart(void);
#line 174
static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$setModeUart(msp430_uart_union_config_t *arg_0x40a920a8);
# 79 "/opt/tinyos-2.x/tos/interfaces/UartStream.nc"
static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$UartStream$receivedByte(uint8_t arg_0x40ccc6f8);
#line 99
static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$UartStream$receiveDone(uint8_t *arg_0x40cca3b8, uint16_t arg_0x40cca540, error_t arg_0x40cca6c0);
#line 57
static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$UartStream$sendDone(uint8_t *arg_0x40ccd608, uint16_t arg_0x40ccd790, error_t arg_0x40ccd910);
# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$release(
# 48 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
uint8_t arg_0x40d26420);
# 87 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$immediateRequest(
# 48 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
uint8_t arg_0x40d26420);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void /*Msp430Uart1P.UartP*/Msp430UartP$0$Resource$granted(
# 42 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
uint8_t arg_0x40cf9738);
#line 59
 uint8_t */*Msp430Uart1P.UartP*/Msp430UartP$0$m_tx_buf;
#line 59
 uint8_t */*Msp430Uart1P.UartP*/Msp430UartP$0$m_rx_buf;
 uint16_t /*Msp430Uart1P.UartP*/Msp430UartP$0$m_tx_len;
#line 60
 uint16_t /*Msp430Uart1P.UartP*/Msp430UartP$0$m_rx_len;
 uint16_t /*Msp430Uart1P.UartP*/Msp430UartP$0$m_tx_pos;
#line 61
 uint16_t /*Msp430Uart1P.UartP*/Msp430UartP$0$m_rx_pos;
 uint8_t /*Msp430Uart1P.UartP*/Msp430UartP$0$m_byte_time;

static inline   error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$Resource$immediateRequest(uint8_t id);
#line 76
static inline   error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$Resource$release(uint8_t id);





static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$ResourceConfigure$configure(uint8_t id);






static inline   void /*Msp430Uart1P.UartP*/Msp430UartP$0$ResourceConfigure$unconfigure(uint8_t id);






static inline  void /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$granted(uint8_t id);
#line 123
static inline   void /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartInterrupts$rxDone(uint8_t data);
#line 137
static   error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$UartStream$send(uint8_t *buf, uint16_t len);
#line 149
static inline   void /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartInterrupts$txDone(void);
#line 186
static inline   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Counter$overflow(void);



static inline    error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$default$immediateRequest(uint8_t id);
static inline    error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$default$release(uint8_t id);
static inline    msp430_uart_union_config_t */*Msp430Uart1P.UartP*/Msp430UartP$0$Msp430UartConfigure$default$getConfig(uint8_t id);



static inline   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Resource$default$granted(uint8_t id);
# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
static   void HplMsp430Usart1P$UCLK$selectIOFunc(void);
# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
static   void HplMsp430Usart1P$Interrupts$rxDone(uint8_t arg_0x40a82ae8);
#line 49
static   void HplMsp430Usart1P$Interrupts$txDone(void);
# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
static   void HplMsp430Usart1P$URXD$selectIOFunc(void);
#line 78
static   void HplMsp430Usart1P$URXD$selectModuleFunc(void);






static   void HplMsp430Usart1P$UTXD$selectIOFunc(void);
#line 78
static   void HplMsp430Usart1P$UTXD$selectModuleFunc(void);






static   void HplMsp430Usart1P$SOMI$selectIOFunc(void);
#line 85
static   void HplMsp430Usart1P$SIMO$selectIOFunc(void);
# 87 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart1P.nc"
 static volatile uint8_t HplMsp430Usart1P$IE2 __asm ("0x0001");
 static volatile uint8_t HplMsp430Usart1P$ME2 __asm ("0x0005");
 static volatile uint8_t HplMsp430Usart1P$IFG2 __asm ("0x0003");
 static volatile uint8_t HplMsp430Usart1P$U1TCTL __asm ("0x0079");
 static volatile uint8_t HplMsp430Usart1P$U1RCTL __asm ("0x007A");
 static volatile uint8_t HplMsp430Usart1P$U1TXBUF __asm ("0x007F");



void sig_UART1RX_VECTOR(void) __attribute((wakeup)) __attribute((interrupt(6)))  ;




void sig_UART1TX_VECTOR(void) __attribute((wakeup)) __attribute((interrupt(4)))  ;



static inline   error_t HplMsp430Usart1P$AsyncStdControl$start(void);



static inline   error_t HplMsp430Usart1P$AsyncStdControl$stop(void);
#line 140
static inline   void HplMsp430Usart1P$Usart$setUbr(uint16_t control);










static inline   void HplMsp430Usart1P$Usart$setUmctl(uint8_t control);







static inline   void HplMsp430Usart1P$Usart$resetUsart(bool reset);
#line 203
static inline   void HplMsp430Usart1P$Usart$enableUart(void);







static   void HplMsp430Usart1P$Usart$disableUart(void);








static inline   void HplMsp430Usart1P$Usart$enableUartTx(void);




static inline   void HplMsp430Usart1P$Usart$disableUartTx(void);





static inline   void HplMsp430Usart1P$Usart$enableUartRx(void);




static inline   void HplMsp430Usart1P$Usart$disableUartRx(void);
#line 251
static   void HplMsp430Usart1P$Usart$disableSpi(void);
#line 283
static inline void HplMsp430Usart1P$configUart(msp430_uart_union_config_t *config);









static inline   void HplMsp430Usart1P$Usart$setModeUart(msp430_uart_union_config_t *config);
#line 347
static inline   void HplMsp430Usart1P$Usart$clrIntr(void);
#line 359
static inline   void HplMsp430Usart1P$Usart$disableIntr(void);
#line 377
static inline   void HplMsp430Usart1P$Usart$enableIntr(void);






static inline   void HplMsp430Usart1P$Usart$tx(uint8_t data);
# 80 "/opt/tinyos-2.x/tos/interfaces/ArbiterInfo.nc"
static   bool /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$ArbiterInfo$inUse(void);







static   uint8_t /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$ArbiterInfo$userId(void);
# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
static   void /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$Interrupts$rxDone(
# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
uint8_t arg_0x40b38d48, 
# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
uint8_t arg_0x40a82ae8);
#line 49
static   void /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$Interrupts$txDone(
# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
uint8_t arg_0x40b38d48);









static inline   void /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$RawInterrupts$txDone(void);




static inline   void /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$RawInterrupts$rxDone(uint8_t data);









static inline    void /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$Interrupts$default$txDone(uint8_t id);
static inline    void /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$Interrupts$default$rxDone(uint8_t id, uint8_t data);
# 39 "/opt/tinyos-2.x/tos/system/FcfsResourceQueueC.nc"
enum /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$__nesc_unnamed4350 {
#line 39
  FcfsResourceQueueC$2$NO_ENTRY = 0xFF
};
uint8_t /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$resQ[1U];
uint8_t /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$qHead = /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$NO_ENTRY;
uint8_t /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$qTail = /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$NO_ENTRY;

static inline  error_t /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$Init$init(void);




static inline   bool /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$FcfsQueue$isEmpty(void);







static inline   resource_client_id_t /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$FcfsQueue$dequeue(void);
# 51 "/opt/tinyos-2.x/tos/interfaces/ResourceRequested.nc"
static   void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceRequested$immediateRequested(
# 55 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089e830);
# 55 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
static   void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceConfigure$unconfigure(
# 60 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089c9b8);
# 49 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
static   void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceConfigure$configure(
# 60 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089c9b8);
# 43 "/opt/tinyos-2.x/tos/interfaces/ResourceQueue.nc"
static   bool /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Queue$isEmpty(void);
#line 60
static   resource_client_id_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Queue$dequeue(void);
# 46 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
static   void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceDefaultOwner$granted(void);
#line 81
static   void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceDefaultOwner$immediateRequested(void);
# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static  void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Resource$granted(
# 54 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
uint8_t arg_0x4089ff00);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$grantedTask$postTask(void);
# 75 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
enum /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$__nesc_unnamed4351 {
#line 75
  ArbiterP$2$grantedTask = 14U
};
#line 75
typedef int /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$__nesc_sillytask_grantedTask[/*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$grantedTask];
#line 67
enum /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$__nesc_unnamed4352 {
#line 67
  ArbiterP$2$RES_CONTROLLED, ArbiterP$2$RES_GRANTING, ArbiterP$2$RES_IMM_GRANTING, ArbiterP$2$RES_BUSY
};
#line 68
enum /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$__nesc_unnamed4353 {
#line 68
  ArbiterP$2$default_owner_id = 1U
};
#line 69
enum /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$__nesc_unnamed4354 {
#line 69
  ArbiterP$2$NO_RES = 0xFF
};
uint8_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$state = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$RES_CONTROLLED;
 uint8_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$resId = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$default_owner_id;
 uint8_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$reqResId;
#line 90
static inline   error_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Resource$immediateRequest(uint8_t id);
#line 108
static inline   error_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Resource$release(uint8_t id);
#line 127
static inline   error_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceDefaultOwner$release(void);
#line 147
static   bool /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ArbiterInfo$inUse(void);
#line 160
static   uint8_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ArbiterInfo$userId(void);
#line 182
static inline  void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$grantedTask$runTask(void);
#line 194
static inline   void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Resource$default$granted(uint8_t id);



static inline    void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceRequested$default$immediateRequested(uint8_t id);









static inline    void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceConfigure$default$configure(uint8_t id);

static inline    void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceConfigure$default$unconfigure(uint8_t id);
# 52 "/opt/tinyos-2.x/tos/lib/power/PowerDownCleanup.nc"
static   void /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$PowerDownCleanup$cleanup(void);
# 56 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
static   error_t /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$ResourceDefaultOwner$release(void);
# 74 "/opt/tinyos-2.x/tos/interfaces/AsyncStdControl.nc"
static   error_t /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$AsyncStdControl$start(void);









static   error_t /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$AsyncStdControl$stop(void);
# 64 "/opt/tinyos-2.x/tos/lib/power/AsyncPowerManagerP.nc"
static inline   void /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$ResourceDefaultOwner$immediateRequested(void);




static inline   void /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$ResourceDefaultOwner$granted(void);




static inline    void /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$PowerDownCleanup$default$cleanup(void);
# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
static   error_t TelosSerialP$Resource$release(void);
#line 87
static   error_t TelosSerialP$Resource$immediateRequest(void);
# 8 "/opt/tinyos-2.x/tos/platforms/telosa/TelosSerialP.nc"
msp430_uart_union_config_t TelosSerialP$msp430_uart_telos_config = { { .ubr = UBR_1MHZ_115200, .umctl = UMCTL_1MHZ_115200, .ssel = 0x02, .pena = 0, .pev = 0, .spb = 0, .clen = 1, .listen = 0, .mm = 0, .ckpl = 0, .urxse = 0, .urxeie = 1, .urxwie = 0, .utxe = 1, .urxe = 1 } };

static inline  error_t TelosSerialP$StdControl$start(void);


static inline  error_t TelosSerialP$StdControl$stop(void);



static inline  void TelosSerialP$Resource$granted(void);

static inline   msp430_uart_union_config_t *TelosSerialP$Msp430UartConfigure$getConfig(void);
# 40 "/opt/tinyos-2.x/tos/lib/serial/SerialPacketInfoActiveMessageP.nc"
static inline   uint8_t SerialPacketInfoActiveMessageP$Info$offset(void);


static inline   uint8_t SerialPacketInfoActiveMessageP$Info$dataLinkLength(message_t *msg, uint8_t upperLen);


static inline   uint8_t SerialPacketInfoActiveMessageP$Info$upperLength(message_t *msg, uint8_t dataLinkLen);
# 99 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
static  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$AMSend$sendDone(message_t *arg_0x40636ea0, error_t arg_0x40635030);
# 64 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
static  error_t /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$Send$send(message_t *arg_0x40bf8a90, uint8_t arg_0x40bf8c10);
# 92 "/opt/tinyos-2.x/tos/interfaces/AMPacket.nc"
static  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$AMPacket$setDestination(message_t *arg_0x40be5ee8, am_addr_t arg_0x40be3088);
#line 151
static  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$AMPacket$setType(message_t *arg_0x40be2d40, am_id_t arg_0x40be2ec0);
# 45 "/opt/tinyos-2.x/tos/system/AMQueueEntryP.nc"
static inline  error_t /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$AMSend$send(am_addr_t dest, 
message_t *msg, 
uint8_t len);









static inline  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$Send$sendDone(message_t *m, error_t err);
# 69 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
static  error_t /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$AMSend$send(
# 40 "/opt/tinyos-2.x/tos/system/AMQueueImplP.nc"
am_id_t arg_0x40defd10, 
# 69 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
am_addr_t arg_0x40637b40, message_t *arg_0x40637ce8, uint8_t arg_0x40637e68);
# 89 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
static  void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Send$sendDone(
# 38 "/opt/tinyos-2.x/tos/system/AMQueueImplP.nc"
uint8_t arg_0x40def3e8, 
# 89 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
message_t *arg_0x40bf6dd8, error_t arg_0x40bf5010);
# 67 "/opt/tinyos-2.x/tos/interfaces/Packet.nc"
static  uint8_t /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Packet$payloadLength(message_t *arg_0x40652010);
#line 83
static  void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Packet$setPayloadLength(message_t *arg_0x40652670, uint8_t arg_0x406527f0);
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static   error_t /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$errorTask$postTask(void);
# 67 "/opt/tinyos-2.x/tos/interfaces/AMPacket.nc"
static  am_addr_t /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$AMPacket$destination(message_t *arg_0x40be5360);
#line 136
static  am_id_t /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$AMPacket$type(message_t *arg_0x40be27c8);
# 118 "/opt/tinyos-2.x/tos/system/AMQueueImplP.nc"
enum /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$__nesc_unnamed4355 {
#line 118
  AMQueueImplP$0$CancelTask = 15U
};
#line 118
typedef int /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$__nesc_sillytask_CancelTask[/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$CancelTask];
#line 161
enum /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$__nesc_unnamed4356 {
#line 161
  AMQueueImplP$0$errorTask = 16U
};
#line 161
typedef int /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$__nesc_sillytask_errorTask[/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$errorTask];
#line 49
#line 47
typedef struct /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$__nesc_unnamed4357 {
  message_t *msg;
} /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$queue_entry_t;

uint8_t /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current = 2;
/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$queue_entry_t /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$queue[2];
uint8_t /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$cancelMask[2 / 8 + 1];

static inline void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$tryToSend(void);

static inline void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$nextPacket(void);
#line 82
static  error_t /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Send$send(uint8_t clientId, message_t *msg, 
uint8_t len);
#line 118
static inline  void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$CancelTask$runTask(void);
#line 155
static void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$sendDone(uint8_t last, message_t *msg, error_t err);





static inline  void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$errorTask$runTask(void);




static inline void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$tryToSend(void);
#line 181
static inline  void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$AMSend$sendDone(am_id_t id, message_t *msg, error_t err);
#line 207
static inline   void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Send$default$sendDone(uint8_t id, message_t *msg, error_t err);
# 99 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
static  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$AMSend$sendDone(message_t *arg_0x40636ea0, error_t arg_0x40635030);
# 64 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
static  error_t /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$Send$send(message_t *arg_0x40bf8a90, uint8_t arg_0x40bf8c10);
# 92 "/opt/tinyos-2.x/tos/interfaces/AMPacket.nc"
static  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$AMPacket$setDestination(message_t *arg_0x40be5ee8, am_addr_t arg_0x40be3088);
#line 151
static  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$AMPacket$setType(message_t *arg_0x40be2d40, am_id_t arg_0x40be2ec0);
# 45 "/opt/tinyos-2.x/tos/system/AMQueueEntryP.nc"
static inline  error_t /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$AMSend$send(am_addr_t dest, 
message_t *msg, 
uint8_t len);









static inline  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$Send$sendDone(message_t *m, error_t err);
# 196 "/opt/tinyos-2.x/tos/chips/msp430/msp430hardware.h"
static inline void __nesc_enable_interrupt(void )
{
   __asm volatile ("eint");}

# 185 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   void /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Timer$overflow(void)
{
}

#line 185
static inline   void /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Timer$overflow(void)
{
}

#line 185
static inline   void /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Timer$overflow(void)
{
}

# 37 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
inline static   void /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$Timer$overflow(void){
#line 37
  /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Timer$overflow();
#line 37
  /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Timer$overflow();
#line 37
  /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Timer$overflow();
#line 37
}
#line 37
# 126 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerP.nc"
static inline   void /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$Overflow$fired(void)
{
  /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$Timer$overflow();
}





static inline    void /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$Event$default$fired(uint8_t n)
{
}

# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
inline static   void /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$Event$fired(uint8_t arg_0x4056f4f0){
#line 28
  switch (arg_0x4056f4f0) {
#line 28
    case 0:
#line 28
      /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Event$fired();
#line 28
      break;
#line 28
    case 1:
#line 28
      /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Event$fired();
#line 28
      break;
#line 28
    case 2:
#line 28
      /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Event$fired();
#line 28
      break;
#line 28
    case 5:
#line 28
      /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$Overflow$fired();
#line 28
      break;
#line 28
    default:
#line 28
      /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$Event$default$fired(arg_0x4056f4f0);
#line 28
      break;
#line 28
    }
#line 28
}
#line 28
# 115 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerP.nc"
static inline   void /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$VectorTimerX0$fired(void)
{
  /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$Event$fired(0);
}

# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
inline static   void Msp430TimerCommonP$VectorTimerA0$fired(void){
#line 28
  /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$VectorTimerX0$fired();
#line 28
}
#line 28
# 47 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$cc_t /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$int2CC(uint16_t x)
#line 47
{
#line 47
  union  {
#line 47
    uint16_t f;
#line 47
    /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$cc_t t;
  } 
#line 47
  c = { .f = x };

#line 47
  return c.t;
}

#line 74
static inline   /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$cc_t /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Control$getControl(void)
{
  return /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$int2CC(* (volatile uint16_t *)354U);
}

#line 177
static inline    void /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Capture$default$captured(uint16_t n)
{
}

# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
inline static   void /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Capture$captured(uint16_t arg_0x40557100){
#line 75
  /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Capture$default$captured(arg_0x40557100);
#line 75
}
#line 75
# 139 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   uint16_t /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Capture$getEvent(void)
{
  return * (volatile uint16_t *)370U;
}

#line 181
static inline    void /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Compare$default$fired(void)
{
}

# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
inline static   void /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Compare$fired(void){
#line 34
  /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Compare$default$fired();
#line 34
}
#line 34
# 47 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$cc_t /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$int2CC(uint16_t x)
#line 47
{
#line 47
  union  {
#line 47
    uint16_t f;
#line 47
    /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$cc_t t;
  } 
#line 47
  c = { .f = x };

#line 47
  return c.t;
}

#line 74
static inline   /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$cc_t /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Control$getControl(void)
{
  return /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$int2CC(* (volatile uint16_t *)356U);
}

#line 177
static inline    void /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Capture$default$captured(uint16_t n)
{
}

# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
inline static   void /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Capture$captured(uint16_t arg_0x40557100){
#line 75
  /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Capture$default$captured(arg_0x40557100);
#line 75
}
#line 75
# 139 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   uint16_t /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Capture$getEvent(void)
{
  return * (volatile uint16_t *)372U;
}

#line 181
static inline    void /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Compare$default$fired(void)
{
}

# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
inline static   void /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Compare$fired(void){
#line 34
  /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Compare$default$fired();
#line 34
}
#line 34
# 47 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$cc_t /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$int2CC(uint16_t x)
#line 47
{
#line 47
  union  {
#line 47
    uint16_t f;
#line 47
    /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$cc_t t;
  } 
#line 47
  c = { .f = x };

#line 47
  return c.t;
}

#line 74
static inline   /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$cc_t /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Control$getControl(void)
{
  return /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$int2CC(* (volatile uint16_t *)358U);
}

#line 177
static inline    void /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Capture$default$captured(uint16_t n)
{
}

# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
inline static   void /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Capture$captured(uint16_t arg_0x40557100){
#line 75
  /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Capture$default$captured(arg_0x40557100);
#line 75
}
#line 75
# 139 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   uint16_t /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Capture$getEvent(void)
{
  return * (volatile uint16_t *)374U;
}

#line 181
static inline    void /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Compare$default$fired(void)
{
}

# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
inline static   void /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Compare$fired(void){
#line 34
  /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Compare$default$fired();
#line 34
}
#line 34
# 120 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerP.nc"
static inline   void /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$VectorTimerX1$fired(void)
{
  uint8_t n = * (volatile uint16_t *)302U;

#line 123
  /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$Event$fired(n >> 1);
}

# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
inline static   void Msp430TimerCommonP$VectorTimerA1$fired(void){
#line 28
  /*Msp430TimerC.Msp430TimerA*/Msp430TimerP$0$VectorTimerX1$fired();
#line 28
}
#line 28
# 115 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerP.nc"
static inline   void /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$VectorTimerX0$fired(void)
{
  /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Event$fired(0);
}

# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
inline static   void Msp430TimerCommonP$VectorTimerB0$fired(void){
#line 28
  /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$VectorTimerX0$fired();
#line 28
}
#line 28
# 185 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   void /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Timer$overflow(void)
{
}

#line 185
static inline   void /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Timer$overflow(void)
{
}

#line 185
static inline   void /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Timer$overflow(void)
{
}

#line 185
static inline   void /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Timer$overflow(void)
{
}

#line 185
static inline   void /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Timer$overflow(void)
{
}

#line 185
static inline   void /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Timer$overflow(void)
{
}

#line 185
static inline   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Timer$overflow(void)
{
}

# 103 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430AlarmC.nc"
static inline   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Timer$overflow(void)
{
}

# 47 "/opt/tinyos-2.x/tos/lib/timer/CounterToLocalTimeC.nc"
static inline   void /*HilTimerMilliC.CounterToLocalTimeC*/CounterToLocalTimeC$0$Counter$overflow(void)
{
}

# 166 "/opt/tinyos-2.x/tos/lib/timer/TransformAlarmC.nc"
static inline   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Counter$overflow(void)
{
}

# 71 "/opt/tinyos-2.x/tos/lib/timer/Counter.nc"
inline static   void /*CounterMilli32C.Transform*/TransformCounterC$0$Counter$overflow(void){
#line 71
  /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Counter$overflow();
#line 71
  /*HilTimerMilliC.CounterToLocalTimeC*/CounterToLocalTimeC$0$Counter$overflow();
#line 71
}
#line 71
# 122 "/opt/tinyos-2.x/tos/lib/timer/TransformCounterC.nc"
static inline   void /*CounterMilli32C.Transform*/TransformCounterC$0$CounterFrom$overflow(void)
{
  /* atomic removed: atomic calls only */
  {
    /*CounterMilli32C.Transform*/TransformCounterC$0$m_upper++;
    if ((/*CounterMilli32C.Transform*/TransformCounterC$0$m_upper & /*CounterMilli32C.Transform*/TransformCounterC$0$OVERFLOW_MASK) == 0) {
      /*CounterMilli32C.Transform*/TransformCounterC$0$Counter$overflow();
      }
  }
}

# 186 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
static inline   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Counter$overflow(void)
#line 186
{
}

# 71 "/opt/tinyos-2.x/tos/lib/timer/Counter.nc"
inline static   void /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Counter$overflow(void){
#line 71
  /*Msp430Uart1P.UartP*/Msp430UartP$0$Counter$overflow();
#line 71
  /*CounterMilli32C.Transform*/TransformCounterC$0$CounterFrom$overflow();
#line 71
}
#line 71
# 53 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430CounterC.nc"
static inline   void /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Msp430Timer$overflow(void)
{
  /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Counter$overflow();
}

# 37 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
inline static   void /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Timer$overflow(void){
#line 37
  /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Msp430Timer$overflow();
#line 37
  /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Timer$overflow();
#line 37
  /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Timer$overflow();
#line 37
  /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Timer$overflow();
#line 37
  /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Timer$overflow();
#line 37
  /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Timer$overflow();
#line 37
  /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Timer$overflow();
#line 37
  /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Timer$overflow();
#line 37
  /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Timer$overflow();
#line 37
}
#line 37
# 126 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerP.nc"
static inline   void /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Overflow$fired(void)
{
  /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Timer$overflow();
}

# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
inline static   error_t /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$fired$postTask(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = SchedulerBasicP$TaskBasic$postTask(/*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$fired);
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 70 "/opt/tinyos-2.x/tos/lib/timer/AlarmToTimerC.nc"
static inline   void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$fired(void)
{
#line 71
  /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$fired$postTask();
}

# 67 "/opt/tinyos-2.x/tos/lib/timer/Alarm.nc"
inline static   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$fired(void){
#line 67
  /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$fired();
#line 67
}
#line 67
# 151 "/opt/tinyos-2.x/tos/lib/timer/TransformAlarmC.nc"
static inline   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$AlarmFrom$fired(void)
{
  /* atomic removed: atomic calls only */
  {
    if (/*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_dt == 0) 
      {
        /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$fired();
      }
    else 
      {
        /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$set_alarm();
      }
  }
}

# 67 "/opt/tinyos-2.x/tos/lib/timer/Alarm.nc"
inline static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Alarm$fired(void){
#line 67
  /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$AlarmFrom$fired();
#line 67
}
#line 67
# 124 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$disableEvents(void)
{
  * (volatile uint16_t *)386U &= ~0x0010;
}

# 40 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerControl.nc"
inline static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430TimerControl$disableEvents(void){
#line 40
  /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$disableEvents();
#line 40
}
#line 40
# 59 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430AlarmC.nc"
static inline   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Compare$fired(void)
{
  /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430TimerControl$disableEvents();
  /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Alarm$fired();
}

# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
inline static   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Compare$fired(void){
#line 34
  /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Compare$fired();
#line 34
}
#line 34
# 139 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   uint16_t /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Capture$getEvent(void)
{
  return * (volatile uint16_t *)402U;
}

#line 177
static inline    void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Capture$default$captured(uint16_t n)
{
}

# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
inline static   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Capture$captured(uint16_t arg_0x40557100){
#line 75
  /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Capture$default$captured(arg_0x40557100);
#line 75
}
#line 75
# 47 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$cc_t /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$int2CC(uint16_t x)
#line 47
{
#line 47
  union  {
#line 47
    uint16_t f;
#line 47
    /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$cc_t t;
  } 
#line 47
  c = { .f = x };

#line 47
  return c.t;
}

#line 74
static inline   /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$cc_t /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$getControl(void)
{
  return /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$int2CC(* (volatile uint16_t *)386U);
}

#line 169
static inline   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Event$fired(void)
{
  if (/*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$getControl().cap) {
    /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Capture$captured(/*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Capture$getEvent());
    }
  else {
#line 174
    /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Compare$fired();
    }
}

# 86 "/opt/tinyos-2.x/tos/system/SchedulerBasicP.nc"
static inline bool SchedulerBasicP$isWaiting(uint8_t id)
{
  return SchedulerBasicP$m_next[id] != SchedulerBasicP$NO_TASK || SchedulerBasicP$m_tail == id;
}

static inline bool SchedulerBasicP$pushTask(uint8_t id)
{
  if (!SchedulerBasicP$isWaiting(id)) 
    {
      if (SchedulerBasicP$m_head == SchedulerBasicP$NO_TASK) 
        {
          SchedulerBasicP$m_head = id;
          SchedulerBasicP$m_tail = id;
        }
      else 
        {
          SchedulerBasicP$m_next[SchedulerBasicP$m_tail] = id;
          SchedulerBasicP$m_tail = id;
        }
      return TRUE;
    }
  else 
    {
      return FALSE;
    }
}

# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
inline static   uint16_t /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Msp430Timer$get(void){
#line 34
  unsigned int result;
#line 34

#line 34
  result = /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Timer$get();
#line 34

#line 34
  return result;
#line 34
}
#line 34
# 38 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430CounterC.nc"
static inline   uint16_t /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Counter$get(void)
{
  return /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Msp430Timer$get();
}

# 53 "/opt/tinyos-2.x/tos/lib/timer/Counter.nc"
inline static   /*CounterMilli32C.Transform*/TransformCounterC$0$CounterFrom$size_type /*CounterMilli32C.Transform*/TransformCounterC$0$CounterFrom$get(void){
#line 53
  unsigned int result;
#line 53

#line 53
  result = /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Counter$get();
#line 53

#line 53
  return result;
#line 53
}
#line 53
# 70 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerP.nc"
static inline   bool /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Timer$isOverflowPending(void)
{
  return * (volatile uint16_t *)384U & 1U;
}

# 35 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
inline static   bool /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Msp430Timer$isOverflowPending(void){
#line 35
  unsigned char result;
#line 35

#line 35
  result = /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Timer$isOverflowPending();
#line 35

#line 35
  return result;
#line 35
}
#line 35
# 43 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430CounterC.nc"
static inline   bool /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Counter$isOverflowPending(void)
{
  return /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Msp430Timer$isOverflowPending();
}

# 60 "/opt/tinyos-2.x/tos/lib/timer/Counter.nc"
inline static   bool /*CounterMilli32C.Transform*/TransformCounterC$0$CounterFrom$isOverflowPending(void){
#line 60
  unsigned char result;
#line 60

#line 60
  result = /*Msp430Counter32khzC.Counter*/Msp430CounterC$0$Counter$isOverflowPending();
#line 60

#line 60
  return result;
#line 60
}
#line 60
# 119 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$enableEvents(void)
{
  * (volatile uint16_t *)386U |= 0x0010;
}

# 39 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerControl.nc"
inline static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430TimerControl$enableEvents(void){
#line 39
  /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$enableEvents();
#line 39
}
#line 39
# 84 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$clearPendingInterrupt(void)
{
  * (volatile uint16_t *)386U &= ~0x0001;
}

# 33 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerControl.nc"
inline static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430TimerControl$clearPendingInterrupt(void){
#line 33
  /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$clearPendingInterrupt();
#line 33
}
#line 33
# 144 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Compare$setEvent(uint16_t x)
{
  * (volatile uint16_t *)402U = x;
}

# 30 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
inline static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Compare$setEvent(uint16_t arg_0x4054f030){
#line 30
  /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Compare$setEvent(arg_0x4054f030);
#line 30
}
#line 30
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
inline static   uint16_t /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Timer$get(void){
#line 34
  unsigned int result;
#line 34

#line 34
  result = /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Timer$get();
#line 34

#line 34
  return result;
#line 34
}
#line 34
# 154 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Compare$setEventFromNow(uint16_t x)
{
  * (volatile uint16_t *)402U = /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Timer$get() + x;
}

# 32 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
inline static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Compare$setEventFromNow(uint16_t arg_0x4054f978){
#line 32
  /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Compare$setEventFromNow(arg_0x4054f978);
#line 32
}
#line 32
# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Timer.nc"
inline static   uint16_t /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Timer$get(void){
#line 34
  unsigned int result;
#line 34

#line 34
  result = /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Timer$get();
#line 34

#line 34
  return result;
#line 34
}
#line 34
# 70 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430AlarmC.nc"
static inline   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Alarm$startAt(uint16_t t0, uint16_t dt)
{
  /* atomic removed: atomic calls only */
  {
    uint16_t now = /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Timer$get();
    uint16_t elapsed = now - t0;

#line 76
    if (elapsed >= dt) 
      {
        /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Compare$setEventFromNow(2);
      }
    else 
      {
        uint16_t remaining = dt - elapsed;

#line 83
        if (remaining <= 2) {
          /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Compare$setEventFromNow(2);
          }
        else {
#line 86
          /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430Compare$setEvent(now + remaining);
          }
      }
#line 88
    /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430TimerControl$clearPendingInterrupt();
    /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430TimerControl$enableEvents();
  }
}

# 92 "/opt/tinyos-2.x/tos/lib/timer/Alarm.nc"
inline static   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$AlarmFrom$startAt(/*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$AlarmFrom$size_type arg_0x408f4c30, /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$AlarmFrom$size_type arg_0x408f4db8){
#line 92
  /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Alarm$startAt(arg_0x408f4c30, arg_0x408f4db8);
#line 92
}
#line 92
# 181 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline    void /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Compare$default$fired(void)
{
}

# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
inline static   void /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Compare$fired(void){
#line 34
  /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Compare$default$fired();
#line 34
}
#line 34
# 139 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   uint16_t /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Capture$getEvent(void)
{
  return * (volatile uint16_t *)404U;
}

#line 177
static inline    void /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Capture$default$captured(uint16_t n)
{
}

# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
inline static   void /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Capture$captured(uint16_t arg_0x40557100){
#line 75
  /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Capture$default$captured(arg_0x40557100);
#line 75
}
#line 75
# 47 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$cc_t /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$int2CC(uint16_t x)
#line 47
{
#line 47
  union  {
#line 47
    uint16_t f;
#line 47
    /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$cc_t t;
  } 
#line 47
  c = { .f = x };

#line 47
  return c.t;
}

#line 74
static inline   /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$cc_t /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Control$getControl(void)
{
  return /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$int2CC(* (volatile uint16_t *)388U);
}

#line 169
static inline   void /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Event$fired(void)
{
  if (/*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Control$getControl().cap) {
    /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Capture$captured(/*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Capture$getEvent());
    }
  else {
#line 174
    /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Compare$fired();
    }
}




static inline    void /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Compare$default$fired(void)
{
}

# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
inline static   void /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Compare$fired(void){
#line 34
  /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Compare$default$fired();
#line 34
}
#line 34
# 139 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   uint16_t /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Capture$getEvent(void)
{
  return * (volatile uint16_t *)406U;
}

#line 177
static inline    void /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Capture$default$captured(uint16_t n)
{
}

# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
inline static   void /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Capture$captured(uint16_t arg_0x40557100){
#line 75
  /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Capture$default$captured(arg_0x40557100);
#line 75
}
#line 75
# 47 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$cc_t /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$int2CC(uint16_t x)
#line 47
{
#line 47
  union  {
#line 47
    uint16_t f;
#line 47
    /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$cc_t t;
  } 
#line 47
  c = { .f = x };

#line 47
  return c.t;
}

#line 74
static inline   /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$cc_t /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Control$getControl(void)
{
  return /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$int2CC(* (volatile uint16_t *)390U);
}

#line 169
static inline   void /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Event$fired(void)
{
  if (/*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Control$getControl().cap) {
    /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Capture$captured(/*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Capture$getEvent());
    }
  else {
#line 174
    /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Compare$fired();
    }
}




static inline    void /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Compare$default$fired(void)
{
}

# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
inline static   void /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Compare$fired(void){
#line 34
  /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Compare$default$fired();
#line 34
}
#line 34
# 139 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   uint16_t /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Capture$getEvent(void)
{
  return * (volatile uint16_t *)408U;
}

#line 177
static inline    void /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Capture$default$captured(uint16_t n)
{
}

# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
inline static   void /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Capture$captured(uint16_t arg_0x40557100){
#line 75
  /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Capture$default$captured(arg_0x40557100);
#line 75
}
#line 75
# 47 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$cc_t /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$int2CC(uint16_t x)
#line 47
{
#line 47
  union  {
#line 47
    uint16_t f;
#line 47
    /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$cc_t t;
  } 
#line 47
  c = { .f = x };

#line 47
  return c.t;
}

#line 74
static inline   /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$cc_t /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Control$getControl(void)
{
  return /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$int2CC(* (volatile uint16_t *)392U);
}

#line 169
static inline   void /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Event$fired(void)
{
  if (/*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Control$getControl().cap) {
    /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Capture$captured(/*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Capture$getEvent());
    }
  else {
#line 174
    /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Compare$fired();
    }
}




static inline    void /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Compare$default$fired(void)
{
}

# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
inline static   void /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Compare$fired(void){
#line 34
  /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Compare$default$fired();
#line 34
}
#line 34
# 139 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   uint16_t /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Capture$getEvent(void)
{
  return * (volatile uint16_t *)410U;
}

#line 177
static inline    void /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Capture$default$captured(uint16_t n)
{
}

# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
inline static   void /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Capture$captured(uint16_t arg_0x40557100){
#line 75
  /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Capture$default$captured(arg_0x40557100);
#line 75
}
#line 75
# 47 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$cc_t /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$int2CC(uint16_t x)
#line 47
{
#line 47
  union  {
#line 47
    uint16_t f;
#line 47
    /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$cc_t t;
  } 
#line 47
  c = { .f = x };

#line 47
  return c.t;
}

#line 74
static inline   /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$cc_t /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Control$getControl(void)
{
  return /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$int2CC(* (volatile uint16_t *)394U);
}

#line 169
static inline   void /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Event$fired(void)
{
  if (/*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Control$getControl().cap) {
    /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Capture$captured(/*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Capture$getEvent());
    }
  else {
#line 174
    /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Compare$fired();
    }
}




static inline    void /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Compare$default$fired(void)
{
}

# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
inline static   void /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Compare$fired(void){
#line 34
  /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Compare$default$fired();
#line 34
}
#line 34
# 139 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   uint16_t /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Capture$getEvent(void)
{
  return * (volatile uint16_t *)412U;
}

#line 177
static inline    void /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Capture$default$captured(uint16_t n)
{
}

# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
inline static   void /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Capture$captured(uint16_t arg_0x40557100){
#line 75
  /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Capture$default$captured(arg_0x40557100);
#line 75
}
#line 75
# 47 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$cc_t /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$int2CC(uint16_t x)
#line 47
{
#line 47
  union  {
#line 47
    uint16_t f;
#line 47
    /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$cc_t t;
  } 
#line 47
  c = { .f = x };

#line 47
  return c.t;
}

#line 74
static inline   /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$cc_t /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Control$getControl(void)
{
  return /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$int2CC(* (volatile uint16_t *)396U);
}

#line 169
static inline   void /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Event$fired(void)
{
  if (/*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Control$getControl().cap) {
    /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Capture$captured(/*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Capture$getEvent());
    }
  else {
#line 174
    /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Compare$fired();
    }
}




static inline    void /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Compare$default$fired(void)
{
}

# 34 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Compare.nc"
inline static   void /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Compare$fired(void){
#line 34
  /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Compare$default$fired();
#line 34
}
#line 34
# 139 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline   uint16_t /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Capture$getEvent(void)
{
  return * (volatile uint16_t *)414U;
}

#line 177
static inline    void /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Capture$default$captured(uint16_t n)
{
}

# 75 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430Capture.nc"
inline static   void /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Capture$captured(uint16_t arg_0x40557100){
#line 75
  /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Capture$default$captured(arg_0x40557100);
#line 75
}
#line 75
# 47 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$cc_t /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$int2CC(uint16_t x)
#line 47
{
#line 47
  union __nesc_unnamed4358 {
#line 47
    uint16_t f;
#line 47
    /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$cc_t t;
  } 
#line 47
  c = { .f = x };

#line 47
  return c.t;
}

#line 74
static inline   /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$cc_t /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Control$getControl(void)
{
  return /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$int2CC(* (volatile uint16_t *)398U);
}

#line 169
static inline   void /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Event$fired(void)
{
  if (/*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Control$getControl().cap) {
    /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Capture$captured(/*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Capture$getEvent());
    }
  else {
#line 174
    /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Compare$fired();
    }
}

# 120 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerP.nc"
static inline   void /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$VectorTimerX1$fired(void)
{
  uint8_t n = * (volatile uint16_t *)286U;

#line 123
  /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Event$fired(n >> 1);
}

# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
inline static   void Msp430TimerCommonP$VectorTimerB1$fired(void){
#line 28
  /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$VectorTimerX1$fired();
#line 28
}
#line 28
# 113 "/opt/tinyos-2.x/tos/system/SchedulerBasicP.nc"
static inline  void SchedulerBasicP$Scheduler$init(void)
{
  /* atomic removed: atomic calls only */
  {
    memset((void *)SchedulerBasicP$m_next, SchedulerBasicP$NO_TASK, sizeof SchedulerBasicP$m_next);
    SchedulerBasicP$m_head = SchedulerBasicP$NO_TASK;
    SchedulerBasicP$m_tail = SchedulerBasicP$NO_TASK;
  }
}

# 46 "/opt/tinyos-2.x/tos/interfaces/Scheduler.nc"
inline static  void RealMainP$Scheduler$init(void){
#line 46
  SchedulerBasicP$Scheduler$init();
#line 46
}
#line 46
# 45 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P56*/HplMsp430GeneralIOP$38$IO$set(void)
#line 45
{
  /* atomic removed: atomic calls only */
#line 45
  * (volatile uint8_t *)49U |= 0x01 << 6;
}

# 34 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void /*PlatformLedsC.Led2Impl*/Msp430GpioC$2$HplGeneralIO$set(void){
#line 34
  /*HplMsp430GeneralIOC.P56*/HplMsp430GeneralIOP$38$IO$set();
#line 34
}
#line 34
# 37 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*PlatformLedsC.Led2Impl*/Msp430GpioC$2$GeneralIO$set(void)
#line 37
{
#line 37
  /*PlatformLedsC.Led2Impl*/Msp430GpioC$2$HplGeneralIO$set();
}

# 29 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
inline static   void LedsP$Led2$set(void){
#line 29
  /*PlatformLedsC.Led2Impl*/Msp430GpioC$2$GeneralIO$set();
#line 29
}
#line 29
# 45 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P55*/HplMsp430GeneralIOP$37$IO$set(void)
#line 45
{
  /* atomic removed: atomic calls only */
#line 45
  * (volatile uint8_t *)49U |= 0x01 << 5;
}

# 34 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$HplGeneralIO$set(void){
#line 34
  /*HplMsp430GeneralIOC.P55*/HplMsp430GeneralIOP$37$IO$set();
#line 34
}
#line 34
# 37 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$GeneralIO$set(void)
#line 37
{
#line 37
  /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$HplGeneralIO$set();
}

# 29 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
inline static   void LedsP$Led1$set(void){
#line 29
  /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$GeneralIO$set();
#line 29
}
#line 29
# 45 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P54*/HplMsp430GeneralIOP$36$IO$set(void)
#line 45
{
  /* atomic removed: atomic calls only */
#line 45
  * (volatile uint8_t *)49U |= 0x01 << 4;
}

# 34 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$HplGeneralIO$set(void){
#line 34
  /*HplMsp430GeneralIOC.P54*/HplMsp430GeneralIOP$36$IO$set();
#line 34
}
#line 34
# 37 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$GeneralIO$set(void)
#line 37
{
#line 37
  /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$HplGeneralIO$set();
}

# 29 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
inline static   void LedsP$Led0$set(void){
#line 29
  /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$GeneralIO$set();
#line 29
}
#line 29
# 52 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P56*/HplMsp430GeneralIOP$38$IO$makeOutput(void)
#line 52
{
  /* atomic removed: atomic calls only */
#line 52
  * (volatile uint8_t *)50U |= 0x01 << 6;
}

# 71 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void /*PlatformLedsC.Led2Impl*/Msp430GpioC$2$HplGeneralIO$makeOutput(void){
#line 71
  /*HplMsp430GeneralIOC.P56*/HplMsp430GeneralIOP$38$IO$makeOutput();
#line 71
}
#line 71
# 43 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*PlatformLedsC.Led2Impl*/Msp430GpioC$2$GeneralIO$makeOutput(void)
#line 43
{
#line 43
  /*PlatformLedsC.Led2Impl*/Msp430GpioC$2$HplGeneralIO$makeOutput();
}

# 35 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
inline static   void LedsP$Led2$makeOutput(void){
#line 35
  /*PlatformLedsC.Led2Impl*/Msp430GpioC$2$GeneralIO$makeOutput();
#line 35
}
#line 35
# 52 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P55*/HplMsp430GeneralIOP$37$IO$makeOutput(void)
#line 52
{
  /* atomic removed: atomic calls only */
#line 52
  * (volatile uint8_t *)50U |= 0x01 << 5;
}

# 71 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$HplGeneralIO$makeOutput(void){
#line 71
  /*HplMsp430GeneralIOC.P55*/HplMsp430GeneralIOP$37$IO$makeOutput();
#line 71
}
#line 71
# 43 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$GeneralIO$makeOutput(void)
#line 43
{
#line 43
  /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$HplGeneralIO$makeOutput();
}

# 35 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
inline static   void LedsP$Led1$makeOutput(void){
#line 35
  /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$GeneralIO$makeOutput();
#line 35
}
#line 35
# 52 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P54*/HplMsp430GeneralIOP$36$IO$makeOutput(void)
#line 52
{
  /* atomic removed: atomic calls only */
#line 52
  * (volatile uint8_t *)50U |= 0x01 << 4;
}

# 71 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$HplGeneralIO$makeOutput(void){
#line 71
  /*HplMsp430GeneralIOC.P54*/HplMsp430GeneralIOP$36$IO$makeOutput();
#line 71
}
#line 71
# 43 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$GeneralIO$makeOutput(void)
#line 43
{
#line 43
  /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$HplGeneralIO$makeOutput();
}

# 35 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
inline static   void LedsP$Led0$makeOutput(void){
#line 35
  /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$GeneralIO$makeOutput();
#line 35
}
#line 35
# 45 "/opt/tinyos-2.x/tos/system/LedsP.nc"
static inline  error_t LedsP$Init$init(void)
#line 45
{
  /* atomic removed: atomic calls only */
#line 46
  {
    ;
    LedsP$Led0$makeOutput();
    LedsP$Led1$makeOutput();
    LedsP$Led2$makeOutput();
    LedsP$Led0$set();
    LedsP$Led1$set();
    LedsP$Led2$set();
  }
  return SUCCESS;
}

# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
inline static  error_t PlatformP$LedsInit$init(void){
#line 51
  unsigned char result;
#line 51

#line 51
  result = LedsP$Init$init();
#line 51

#line 51
  return result;
#line 51
}
#line 51
# 33 "/opt/tinyos-2.x/tos/platforms/telosb/hardware.h"
static inline void TOSH_SET_SIMO0_PIN(void)
#line 33
{
#line 33
   static volatile uint8_t r __asm ("0x0019");

#line 33
  r |= 1 << 1;
}

#line 34
static inline void TOSH_SET_UCLK0_PIN(void)
#line 34
{
#line 34
   static volatile uint8_t r __asm ("0x0019");

#line 34
  r |= 1 << 3;
}

#line 85
static inline void TOSH_SET_FLASH_CS_PIN(void)
#line 85
{
#line 85
   static volatile uint8_t r __asm ("0x001D");

#line 85
  r |= 1 << 4;
}

#line 34
static inline void TOSH_CLR_UCLK0_PIN(void)
#line 34
{
#line 34
   static volatile uint8_t r __asm ("0x0019");

#line 34
  r &= ~(1 << 3);
}

#line 85
static inline void TOSH_CLR_FLASH_CS_PIN(void)
#line 85
{
#line 85
   static volatile uint8_t r __asm ("0x001D");

#line 85
  r &= ~(1 << 4);
}

# 11 "/opt/tinyos-2.x/tos/platforms/telosb/MotePlatformC.nc"
static __inline void MotePlatformC$TOSH_wait(void)
#line 11
{
   __asm volatile ("nop"); __asm volatile ("nop");}

# 86 "/opt/tinyos-2.x/tos/platforms/telosb/hardware.h"
static inline void TOSH_SET_FLASH_HOLD_PIN(void)
#line 86
{
#line 86
   static volatile uint8_t r __asm ("0x001D");

#line 86
  r |= 1 << 7;
}

#line 85
static inline void TOSH_MAKE_FLASH_CS_OUTPUT(void)
#line 85
{
#line 85
   static volatile uint8_t r __asm ("0x001E");

#line 85
  r |= 1 << 4;
}

#line 86
static inline void TOSH_MAKE_FLASH_HOLD_OUTPUT(void)
#line 86
{
#line 86
   static volatile uint8_t r __asm ("0x001E");

#line 86
  r |= 1 << 7;
}

#line 34
static inline void TOSH_MAKE_UCLK0_OUTPUT(void)
#line 34
{
#line 34
   static volatile uint8_t r __asm ("0x001A");

#line 34
  r |= 1 << 3;
}

#line 33
static inline void TOSH_MAKE_SIMO0_OUTPUT(void)
#line 33
{
#line 33
   static volatile uint8_t r __asm ("0x001A");

#line 33
  r |= 1 << 1;
}

# 27 "/opt/tinyos-2.x/tos/platforms/telosb/MotePlatformC.nc"
static inline void MotePlatformC$TOSH_FLASH_M25P_DP(void)
#line 27
{

  TOSH_MAKE_SIMO0_OUTPUT();
  TOSH_MAKE_UCLK0_OUTPUT();
  TOSH_MAKE_FLASH_HOLD_OUTPUT();
  TOSH_MAKE_FLASH_CS_OUTPUT();
  TOSH_SET_FLASH_HOLD_PIN();
  TOSH_SET_FLASH_CS_PIN();

  MotePlatformC$TOSH_wait();


  TOSH_CLR_FLASH_CS_PIN();
  TOSH_CLR_UCLK0_PIN();

  MotePlatformC$TOSH_FLASH_M25P_DP_bit(TRUE);
  MotePlatformC$TOSH_FLASH_M25P_DP_bit(FALSE);
  MotePlatformC$TOSH_FLASH_M25P_DP_bit(TRUE);
  MotePlatformC$TOSH_FLASH_M25P_DP_bit(TRUE);
  MotePlatformC$TOSH_FLASH_M25P_DP_bit(TRUE);
  MotePlatformC$TOSH_FLASH_M25P_DP_bit(FALSE);
  MotePlatformC$TOSH_FLASH_M25P_DP_bit(FALSE);
  MotePlatformC$TOSH_FLASH_M25P_DP_bit(TRUE);

  TOSH_SET_FLASH_CS_PIN();
  TOSH_SET_UCLK0_PIN();
  TOSH_SET_SIMO0_PIN();
}

#line 6
static __inline void MotePlatformC$uwait(uint16_t u)
#line 6
{
  uint16_t t0 = TA0R;

#line 8
  while (TA0R - t0 <= u) ;
}

#line 56
static inline  error_t MotePlatformC$Init$init(void)
#line 56
{
  /* atomic removed: atomic calls only */

  {
    P1SEL = 0;
    P2SEL = 0;
    P3SEL = 0;
    P4SEL = 0;
    P5SEL = 0;
    P6SEL = 0;

    P1DIR = 0xe0;
    P1OUT = 0x00;

    P2DIR = 0x7b;
    P2OUT = 0x30;

    P3DIR = 0xf1;
    P3OUT = 0x00;

    P4DIR = 0xfd;
    P4OUT = 0xdd;

    P5DIR = 0xff;
    P5OUT = 0xff;

    P6DIR = 0xff;
    P6OUT = 0x00;

    P1IE = 0;
    P2IE = 0;






    MotePlatformC$uwait(1024 * 10);

    MotePlatformC$TOSH_FLASH_M25P_DP();
  }

  return SUCCESS;
}

# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
inline static  error_t PlatformP$MoteInit$init(void){
#line 51
  unsigned char result;
#line 51

#line 51
  result = MotePlatformC$Init$init();
#line 51

#line 51
  return result;
#line 51
}
#line 51
# 148 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430ClockP.nc"
static inline void Msp430ClockP$startTimerB(void)
{

  Msp430ClockP$TBCTL = 0x0020 | (Msp430ClockP$TBCTL & ~(0x0020 | 0x0010));
}

#line 136
static inline void Msp430ClockP$startTimerA(void)
{

  Msp430ClockP$TA0CTL = 0x0020 | (Msp430ClockP$TA0CTL & ~(0x0020 | 0x0010));
}

#line 100
static inline  void Msp430ClockP$Msp430ClockInit$defaultInitTimerB(void)
{
  TBR = 0;









  Msp430ClockP$TBCTL = 0x0100 | 0x0002;
}

#line 130
static inline   void Msp430ClockP$Msp430ClockInit$default$initTimerB(void)
{
  Msp430ClockP$Msp430ClockInit$defaultInitTimerB();
}

# 32 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430ClockInit.nc"
inline static  void Msp430ClockP$Msp430ClockInit$initTimerB(void){
#line 32
  Msp430ClockP$Msp430ClockInit$default$initTimerB();
#line 32
}
#line 32
# 85 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430ClockP.nc"
static inline  void Msp430ClockP$Msp430ClockInit$defaultInitTimerA(void)
{
  TA0R = 0;









  Msp430ClockP$TA0CTL = 0x0200 | 0x0002;
}

#line 125
static inline   void Msp430ClockP$Msp430ClockInit$default$initTimerA(void)
{
  Msp430ClockP$Msp430ClockInit$defaultInitTimerA();
}

# 31 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430ClockInit.nc"
inline static  void Msp430ClockP$Msp430ClockInit$initTimerA(void){
#line 31
  Msp430ClockP$Msp430ClockInit$default$initTimerA();
#line 31
}
#line 31
# 64 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430ClockP.nc"
static inline  void Msp430ClockP$Msp430ClockInit$defaultInitClocks(void)
{





  BCSCTL1 = 0x80 | (BCSCTL1 & ((0x04 | 0x02) | 0x01));







  BCSCTL2 = 0x04;


  Msp430ClockP$IE1 &= ~(1 << 1);
}

#line 120
static inline   void Msp430ClockP$Msp430ClockInit$default$initClocks(void)
{
  Msp430ClockP$Msp430ClockInit$defaultInitClocks();
}

# 30 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430ClockInit.nc"
inline static  void Msp430ClockP$Msp430ClockInit$initClocks(void){
#line 30
  Msp430ClockP$Msp430ClockInit$default$initClocks();
#line 30
}
#line 30
# 166 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430ClockP.nc"
static inline uint16_t Msp430ClockP$test_calib_busywait_delta(int calib)
{
  int8_t aclk_count = 2;
  uint16_t dco_prev = 0;
  uint16_t dco_curr = 0;

  Msp430ClockP$set_dco_calib(calib);

  while (aclk_count-- > 0) 
    {
      TBCCR0 = TBR + Msp430ClockP$ACLK_CALIB_PERIOD;
      TBCCTL0 &= ~0x0001;
      while ((TBCCTL0 & 0x0001) == 0) ;
      dco_prev = dco_curr;
      dco_curr = TA0R;
    }

  return dco_curr - dco_prev;
}




static inline void Msp430ClockP$busyCalibrateDco(void)
{

  int calib;
  int step;






  for (calib = 0, step = 0x800; step != 0; step >>= 1) 
    {

      if (Msp430ClockP$test_calib_busywait_delta(calib | step) <= Msp430ClockP$TARGET_DCO_DELTA) {
        calib |= step;
        }
    }

  if ((calib & 0x0e0) == 0x0e0) {
    calib &= ~0x01f;
    }
  Msp430ClockP$set_dco_calib(calib);
}

#line 52
static inline  void Msp430ClockP$Msp430ClockInit$defaultSetupDcoCalibrate(void)
{



  Msp430ClockP$TA0CTL = 0x0200 | 0x0020;
  Msp430ClockP$TBCTL = 0x0100 | 0x0020;
  BCSCTL1 = 0x80 | 0x04;
  BCSCTL2 = 0;
  TBCCTL0 = 0x4000;
}

#line 115
static inline   void Msp430ClockP$Msp430ClockInit$default$setupDcoCalibrate(void)
{
  Msp430ClockP$Msp430ClockInit$defaultSetupDcoCalibrate();
}

# 29 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430ClockInit.nc"
inline static  void Msp430ClockP$Msp430ClockInit$setupDcoCalibrate(void){
#line 29
  Msp430ClockP$Msp430ClockInit$default$setupDcoCalibrate();
#line 29
}
#line 29
# 214 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430ClockP.nc"
static inline  error_t Msp430ClockP$Init$init(void)
{

  Msp430ClockP$TA0CTL = 0x0004;
  Msp430ClockP$TA0IV = 0;
  Msp430ClockP$TBCTL = 0x0004;
  Msp430ClockP$TBIV = 0;
  /* atomic removed: atomic calls only */

  {
    Msp430ClockP$Msp430ClockInit$setupDcoCalibrate();
    Msp430ClockP$busyCalibrateDco();
    Msp430ClockP$Msp430ClockInit$initClocks();
    Msp430ClockP$Msp430ClockInit$initTimerA();
    Msp430ClockP$Msp430ClockInit$initTimerB();
    Msp430ClockP$startTimerA();
    Msp430ClockP$startTimerB();
  }

  return SUCCESS;
}

# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
inline static  error_t PlatformP$MoteClockInit$init(void){
#line 51
  unsigned char result;
#line 51

#line 51
  result = Msp430ClockP$Init$init();
#line 51

#line 51
  return result;
#line 51
}
#line 51
# 10 "/opt/tinyos-2.x/tos/platforms/telosa/PlatformP.nc"
static inline  error_t PlatformP$Init$init(void)
#line 10
{
  PlatformP$MoteClockInit$init();
  PlatformP$MoteInit$init();
  PlatformP$LedsInit$init();
  return SUCCESS;
}

# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
inline static  error_t RealMainP$PlatformInit$init(void){
#line 51
  unsigned char result;
#line 51

#line 51
  result = PlatformP$Init$init();
#line 51

#line 51
  return result;
#line 51
}
#line 51
# 33 "/opt/tinyos-2.x/tos/platforms/telosb/hardware.h"
static inline void TOSH_CLR_SIMO0_PIN(void)
#line 33
{
#line 33
   static volatile uint8_t r __asm ("0x0019");

#line 33
  r &= ~(1 << 1);
}

# 54 "/opt/tinyos-2.x/tos/interfaces/Scheduler.nc"
inline static  bool RealMainP$Scheduler$runNextTask(void){
#line 54
  unsigned char result;
#line 54

#line 54
  result = SchedulerBasicP$Scheduler$runNextTask();
#line 54

#line 54
  return result;
#line 54
}
#line 54
# 99 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
inline static  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$AMSend$sendDone(message_t *arg_0x40636ea0, error_t arg_0x40635030){
#line 99
  TraceRetrieverP$UARTSendData$sendDone(arg_0x40636ea0, arg_0x40635030);
#line 99
}
#line 99
# 57 "/opt/tinyos-2.x/tos/system/AMQueueEntryP.nc"
static inline  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$Send$sendDone(message_t *m, error_t err)
#line 57
{
  /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$AMSend$sendDone(m, err);
}

# 44 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$HplGeneralIO$toggle(void){
#line 44
  /*HplMsp430GeneralIOC.P55*/HplMsp430GeneralIOP$37$IO$toggle();
#line 44
}
#line 44
# 39 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$GeneralIO$toggle(void)
#line 39
{
#line 39
  /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$HplGeneralIO$toggle();
}

# 31 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
inline static   void LedsP$Led1$toggle(void){
#line 31
  /*PlatformLedsC.Led1Impl*/Msp430GpioC$1$GeneralIO$toggle();
#line 31
}
#line 31
# 88 "/opt/tinyos-2.x/tos/system/LedsP.nc"
static inline   void LedsP$Leds$led1Toggle(void)
#line 88
{
  LedsP$Led1$toggle();
  ;
#line 90
  ;
}

# 72 "/opt/tinyos-2.x/tos/interfaces/Leds.nc"
inline static   void TraceRetrieverP$Leds$led1Toggle(void){
#line 72
  LedsP$Leds$led1Toggle();
#line 72
}
#line 72
# 123 "TraceRetrieverP.nc"
static inline  void TraceRetrieverP$UARTSendAck$sendDone(message_t *bufPtr, error_t error)
#line 123
{
  TraceRetrieverP$reading = FALSE;
  TraceRetrieverP$Leds$led1Toggle();
}

# 99 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
inline static  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$AMSend$sendDone(message_t *arg_0x40636ea0, error_t arg_0x40635030){
#line 99
  TraceRetrieverP$UARTSendAck$sendDone(arg_0x40636ea0, arg_0x40635030);
#line 99
}
#line 99
# 57 "/opt/tinyos-2.x/tos/system/AMQueueEntryP.nc"
static inline  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$Send$sendDone(message_t *m, error_t err)
#line 57
{
  /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$AMSend$sendDone(m, err);
}

# 207 "/opt/tinyos-2.x/tos/system/AMQueueImplP.nc"
static inline   void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Send$default$sendDone(uint8_t id, message_t *msg, error_t err)
#line 207
{
}

# 89 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
inline static  void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Send$sendDone(uint8_t arg_0x40def3e8, message_t *arg_0x40bf6dd8, error_t arg_0x40bf5010){
#line 89
  switch (arg_0x40def3e8) {
#line 89
    case 0U:
#line 89
      /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$Send$sendDone(arg_0x40bf6dd8, arg_0x40bf5010);
#line 89
      break;
#line 89
    case 1U:
#line 89
      /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$Send$sendDone(arg_0x40bf6dd8, arg_0x40bf5010);
#line 89
      break;
#line 89
    default:
#line 89
      /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Send$default$sendDone(arg_0x40def3e8, arg_0x40bf6dd8, arg_0x40bf5010);
#line 89
      break;
#line 89
    }
#line 89
}
#line 89
# 118 "/opt/tinyos-2.x/tos/system/AMQueueImplP.nc"
static inline  void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$CancelTask$runTask(void)
#line 118
{
  uint8_t i;
#line 119
  uint8_t j;
#line 119
  uint8_t mask;
#line 119
  uint8_t last;
  message_t *msg;

#line 121
  for (i = 0; i < 2 / 8 + 1; i++) {
      if (/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$cancelMask[i]) {
          for (mask = 1, j = 0; j < 8; j++) {
              if (/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$cancelMask[i] & mask) {
                  last = i * 8 + j;
                  msg = /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$queue[last].msg;
                  /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$queue[last].msg = (void *)0;
                  /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$cancelMask[i] &= ~mask;
                  /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Send$sendDone(last, msg, ECANCEL);
                }
              mask <<= 1;
            }
        }
    }
}

# 145 "/opt/nesc-1.2.7a/lib/ncc/nesc_nx.h"
static __inline uint8_t __nesc_hton_uint8(void *target, uint8_t value)
#line 145
{
  uint8_t *base = target;

#line 147
  base[0] = value;
  return value;
}

# 49 "/opt/tinyos-2.x/tos/lib/serial/SerialActiveMessageP.nc"
static inline serial_header_t */*SerialActiveMessageC.AM*/SerialActiveMessageP$0$getHeader(message_t *msg)
#line 49
{
  return (serial_header_t *)(msg->data - sizeof(serial_header_t ));
}

#line 106
static inline  void /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Packet$setPayloadLength(message_t *msg, uint8_t len)
#line 106
{
  __nesc_hton_uint8((unsigned char *)&/*SerialActiveMessageC.AM*/SerialActiveMessageP$0$getHeader(msg)->length, len);
}

# 83 "/opt/tinyos-2.x/tos/interfaces/Packet.nc"
inline static  void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Packet$setPayloadLength(message_t *arg_0x40652670, uint8_t arg_0x406527f0){
#line 83
  /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Packet$setPayloadLength(arg_0x40652670, arg_0x406527f0);
#line 83
}
#line 83
# 169 "/opt/nesc-1.2.7a/lib/ncc/nesc_nx.h"
static __inline uint16_t __nesc_ntoh_uint16(const void *source)
#line 169
{
  const uint8_t *base = source;

#line 171
  return ((uint16_t )base[0] << 8) | base[1];
}

# 522 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static inline   error_t SerialP$SendBytePacket$startSend(uint8_t b)
#line 522
{
  bool not_busy = FALSE;

#line 524
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 524
    {
      if (SerialP$txBuf[SerialP$TX_DATA_INDEX].state == SerialP$BUFFER_AVAILABLE) {
          SerialP$txBuf[SerialP$TX_DATA_INDEX].state = SerialP$BUFFER_FILLING;
          SerialP$txBuf[SerialP$TX_DATA_INDEX].buf = b;
          not_busy = TRUE;
        }
    }
#line 530
    __nesc_atomic_end(__nesc_atomic); }
  if (not_busy) {
      SerialP$MaybeScheduleTx();
      return SUCCESS;
    }
  return EBUSY;
}

# 51 "/opt/tinyos-2.x/tos/lib/serial/SendBytePacket.nc"
inline static   error_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SendBytePacket$startSend(uint8_t arg_0x40c3a658){
#line 51
  unsigned char result;
#line 51

#line 51
  result = SerialP$SendBytePacket$startSend(arg_0x40c3a658);
#line 51

#line 51
  return result;
#line 51
}
#line 51
# 43 "/opt/tinyos-2.x/tos/lib/serial/SerialPacketInfoActiveMessageP.nc"
static inline   uint8_t SerialPacketInfoActiveMessageP$Info$dataLinkLength(message_t *msg, uint8_t upperLen)
#line 43
{
  return upperLen + sizeof(serial_header_t );
}

# 347 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
static inline    uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$default$dataLinkLength(uart_id_t id, message_t *msg, 
uint8_t upperLen)
#line 348
{
  return 0;
}

# 23 "/opt/tinyos-2.x/tos/lib/serial/SerialPacketInfo.nc"
inline static   uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$dataLinkLength(uart_id_t arg_0x40cae450, message_t *arg_0x40c19010, uint8_t arg_0x40c19198){
#line 23
  unsigned char result;
#line 23

#line 23
  switch (arg_0x40cae450) {
#line 23
    case TOS_SERIAL_ACTIVE_MESSAGE_ID:
#line 23
      result = SerialPacketInfoActiveMessageP$Info$dataLinkLength(arg_0x40c19010, arg_0x40c19198);
#line 23
      break;
#line 23
    default:
#line 23
      result = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$default$dataLinkLength(arg_0x40cae450, arg_0x40c19010, arg_0x40c19198);
#line 23
      break;
#line 23
    }
#line 23

#line 23
  return result;
#line 23
}
#line 23
# 40 "/opt/tinyos-2.x/tos/lib/serial/SerialPacketInfoActiveMessageP.nc"
static inline   uint8_t SerialPacketInfoActiveMessageP$Info$offset(void)
#line 40
{
  return (uint8_t )(sizeof(message_header_t ) - sizeof(serial_header_t ));
}

# 344 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
static inline    uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$default$offset(uart_id_t id)
#line 344
{
  return 0;
}

# 15 "/opt/tinyos-2.x/tos/lib/serial/SerialPacketInfo.nc"
inline static   uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$offset(uart_id_t arg_0x40cae450){
#line 15
  unsigned char result;
#line 15

#line 15
  switch (arg_0x40cae450) {
#line 15
    case TOS_SERIAL_ACTIVE_MESSAGE_ID:
#line 15
      result = SerialPacketInfoActiveMessageP$Info$offset();
#line 15
      break;
#line 15
    default:
#line 15
      result = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$default$offset(arg_0x40cae450);
#line 15
      break;
#line 15
    }
#line 15

#line 15
  return result;
#line 15
}
#line 15
# 100 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
static inline  error_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Send$send(uint8_t id, message_t *msg, uint8_t len)
#line 100
{
  if (/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendState != /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SEND_STATE_IDLE) {
      return EBUSY;
    }

  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 105
    {
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendIndex = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$offset(id);
      if (/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendIndex > sizeof(message_header_t )) {
          {
            unsigned char __nesc_temp = 
#line 108
            ESIZE;

            {
#line 108
              __nesc_atomic_end(__nesc_atomic); 
#line 108
              return __nesc_temp;
            }
          }
        }
#line 111
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendError = SUCCESS;
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendBuffer = (uint8_t *)msg;
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendState = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SEND_STATE_DATA;
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendId = id;
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendCancelled = FALSE;






      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendLen = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$dataLinkLength(id, msg, len) + /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendIndex;
    }
#line 123
    __nesc_atomic_end(__nesc_atomic); }
  if (/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SendBytePacket$startSend(id) == SUCCESS) {
      return SUCCESS;
    }
  else {
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendState = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SEND_STATE_IDLE;
      return FAIL;
    }
}

# 64 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
inline static  error_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$SubSend$send(message_t *arg_0x40bf8a90, uint8_t arg_0x40bf8c10){
#line 64
  unsigned char result;
#line 64

#line 64
  result = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Send$send(TOS_SERIAL_ACTIVE_MESSAGE_ID, arg_0x40bf8a90, arg_0x40bf8c10);
#line 64

#line 64
  return result;
#line 64
}
#line 64
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
inline static   error_t SerialP$RunTx$postTask(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = SchedulerBasicP$TaskBasic$postTask(SerialP$RunTx);
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 161 "/opt/tinyos-2.x/tos/system/AMQueueImplP.nc"
static inline  void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$errorTask$runTask(void)
#line 161
{
  /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$sendDone(/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current, /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$queue[/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current].msg, FAIL);
}

# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
inline static   error_t /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$errorTask$postTask(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = SchedulerBasicP$TaskBasic$postTask(/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$errorTask);
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 69 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
inline static  error_t /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$AMSend$send(am_id_t arg_0x40defd10, am_addr_t arg_0x40637b40, message_t *arg_0x40637ce8, uint8_t arg_0x40637e68){
#line 69
  unsigned char result;
#line 69

#line 69
  result = /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMSend$send(arg_0x40defd10, arg_0x40637b40, arg_0x40637ce8, arg_0x40637e68);
#line 69

#line 69
  return result;
#line 69
}
#line 69
# 140 "/opt/nesc-1.2.7a/lib/ncc/nesc_nx.h"
static __inline uint8_t __nesc_ntoh_uint8(const void *source)
#line 140
{
  const uint8_t *base = source;

#line 142
  return base[0];
}

# 101 "/opt/tinyos-2.x/tos/lib/serial/SerialActiveMessageP.nc"
static inline  uint8_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Packet$payloadLength(message_t *msg)
#line 101
{
  serial_header_t *header = /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$getHeader(msg);

#line 103
  return __nesc_ntoh_uint8((unsigned char *)&header->length);
}

# 67 "/opt/tinyos-2.x/tos/interfaces/Packet.nc"
inline static  uint8_t /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Packet$payloadLength(message_t *arg_0x40652010){
#line 67
  unsigned char result;
#line 67

#line 67
  result = /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Packet$payloadLength(arg_0x40652010);
#line 67

#line 67
  return result;
#line 67
}
#line 67
# 67 "/opt/tinyos-2.x/tos/interfaces/AMPacket.nc"
inline static  am_addr_t /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$AMPacket$destination(message_t *arg_0x40be5360){
#line 67
  unsigned int result;
#line 67

#line 67
  result = /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$destination(arg_0x40be5360);
#line 67

#line 67
  return result;
#line 67
}
#line 67
#line 136
inline static  am_id_t /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$AMPacket$type(message_t *arg_0x40be27c8){
#line 136
  unsigned char result;
#line 136

#line 136
  result = /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$type(arg_0x40be27c8);
#line 136

#line 136
  return result;
#line 136
}
#line 136
# 57 "/opt/tinyos-2.x/tos/system/AMQueueImplP.nc"
static inline void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$nextPacket(void)
#line 57
{
  uint8_t i;

#line 59
  /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current = (/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current + 1) % 2;
  for (i = 0; i < 2; i++) {
      if (/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$queue[/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current].msg == (void *)0 || 
      /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$cancelMask[/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current / 8] & (1 << /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current % 8)) 
        {
          /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current = (/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current + 1) % 2;
        }
      else {
          break;
        }
    }
  if (i >= 2) {
#line 70
    /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current = 2;
    }
}

#line 166
static inline void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$tryToSend(void)
#line 166
{
  /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$nextPacket();
  if (/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current < 2) {
      error_t nextErr;
      message_t *nextMsg = /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$queue[/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current].msg;
      am_id_t nextId = /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$AMPacket$type(nextMsg);
      am_addr_t nextDest = /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$AMPacket$destination(nextMsg);
      uint8_t len = /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Packet$payloadLength(nextMsg);

#line 174
      nextErr = /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$AMSend$send(nextId, nextDest, nextMsg, len);
      if (nextErr != SUCCESS) {
          /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$errorTask$postTask();
        }
    }
}

# 17 "/opt/tinyos-2.x/tos/platforms/telosa/TelosSerialP.nc"
static inline  void TelosSerialP$Resource$granted(void)
#line 17
{
}

# 196 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
static inline   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Resource$default$granted(uint8_t id)
#line 196
{
}

# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static  void /*Msp430Uart1P.UartP*/Msp430UartP$0$Resource$granted(uint8_t arg_0x40cf9738){
#line 92
  switch (arg_0x40cf9738) {
#line 92
    case /*PlatformSerialC.UartC*/Msp430Uart1C$0$CLIENT_ID:
#line 92
      TelosSerialP$Resource$granted();
#line 92
      break;
#line 92
    default:
#line 92
      /*Msp430Uart1P.UartP*/Msp430UartP$0$Resource$default$granted(arg_0x40cf9738);
#line 92
      break;
#line 92
    }
#line 92
}
#line 92
# 96 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
static inline  void /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$granted(uint8_t id)
#line 96
{
  /*Msp430Uart1P.UartP*/Msp430UartP$0$Resource$granted(id);
}

# 194 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline   void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Resource$default$granted(uint8_t id)
#line 194
{
}

# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static  void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Resource$granted(uint8_t arg_0x4089ff00){
#line 92
  switch (arg_0x4089ff00) {
#line 92
    case /*PlatformSerialC.UartC.UsartC*/Msp430Usart1C$0$CLIENT_ID:
#line 92
      /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$granted(/*PlatformSerialC.UartC*/Msp430Uart1C$0$CLIENT_ID);
#line 92
      break;
#line 92
    default:
#line 92
      /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Resource$default$granted(arg_0x4089ff00);
#line 92
      break;
#line 92
    }
#line 92
}
#line 92
# 208 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline    void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceConfigure$default$configure(uint8_t id)
#line 208
{
}

# 49 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
inline static   void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceConfigure$configure(uint8_t arg_0x4089c9b8){
#line 49
  switch (arg_0x4089c9b8) {
#line 49
    case /*PlatformSerialC.UartC.UsartC*/Msp430Usart1C$0$CLIENT_ID:
#line 49
      /*Msp430Uart1P.UartP*/Msp430UartP$0$ResourceConfigure$configure(/*PlatformSerialC.UartC*/Msp430Uart1C$0$CLIENT_ID);
#line 49
      break;
#line 49
    default:
#line 49
      /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceConfigure$default$configure(arg_0x4089c9b8);
#line 49
      break;
#line 49
    }
#line 49
}
#line 49
# 182 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline  void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$grantedTask$runTask(void)
#line 182
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 183
    {
      /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$resId = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$reqResId;
      /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$state = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$RES_BUSY;
    }
#line 186
    __nesc_atomic_end(__nesc_atomic); }
  /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceConfigure$configure(/*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$resId);
  /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Resource$granted(/*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$resId);
}

# 19 "/opt/tinyos-2.x/tos/platforms/telosa/TelosSerialP.nc"
static inline   msp430_uart_union_config_t *TelosSerialP$Msp430UartConfigure$getConfig(void)
#line 19
{
  return &TelosSerialP$msp430_uart_telos_config;
}

# 192 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
static inline    msp430_uart_union_config_t */*Msp430Uart1P.UartP*/Msp430UartP$0$Msp430UartConfigure$default$getConfig(uint8_t id)
#line 192
{
  return &msp430_uart_default_config;
}

# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartConfigure.nc"
inline static   msp430_uart_union_config_t */*Msp430Uart1P.UartP*/Msp430UartP$0$Msp430UartConfigure$getConfig(uint8_t arg_0x40d26d48){
#line 39
  union __nesc_unnamed4289 *result;
#line 39

#line 39
  switch (arg_0x40d26d48) {
#line 39
    case /*PlatformSerialC.UartC*/Msp430Uart1C$0$CLIENT_ID:
#line 39
      result = TelosSerialP$Msp430UartConfigure$getConfig();
#line 39
      break;
#line 39
    default:
#line 39
      result = /*Msp430Uart1P.UartP*/Msp430UartP$0$Msp430UartConfigure$default$getConfig(arg_0x40d26d48);
#line 39
      break;
#line 39
    }
#line 39

#line 39
  return result;
#line 39
}
#line 39
# 359 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart1P.nc"
static inline   void HplMsp430Usart1P$Usart$disableIntr(void)
#line 359
{
  HplMsp430Usart1P$IE2 &= ~((1 << 5) | (1 << 4));
}

#line 347
static inline   void HplMsp430Usart1P$Usart$clrIntr(void)
#line 347
{
  HplMsp430Usart1P$IFG2 &= ~((1 << 5) | (1 << 4));
}

#line 159
static inline   void HplMsp430Usart1P$Usart$resetUsart(bool reset)
#line 159
{
  if (reset) {
    U1CTL = 0x01;
    }
  else {
#line 163
    U1CTL &= ~0x01;
    }
}

# 54 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P36*/HplMsp430GeneralIOP$22$IO$selectModuleFunc(void)
#line 54
{
  /* atomic removed: atomic calls only */
#line 54
  * (volatile uint8_t *)27U |= 0x01 << 6;
}

# 78 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void HplMsp430Usart1P$UTXD$selectModuleFunc(void){
#line 78
  /*HplMsp430GeneralIOC.P36*/HplMsp430GeneralIOP$22$IO$selectModuleFunc();
#line 78
}
#line 78
# 220 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart1P.nc"
static inline   void HplMsp430Usart1P$Usart$enableUartTx(void)
#line 220
{
  HplMsp430Usart1P$UTXD$selectModuleFunc();
  HplMsp430Usart1P$ME2 |= 1 << 5;
}

# 56 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P37*/HplMsp430GeneralIOP$23$IO$selectIOFunc(void)
#line 56
{
  /* atomic removed: atomic calls only */
#line 56
  * (volatile uint8_t *)27U &= ~(0x01 << 7);
}

# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void HplMsp430Usart1P$URXD$selectIOFunc(void){
#line 85
  /*HplMsp430GeneralIOC.P37*/HplMsp430GeneralIOP$23$IO$selectIOFunc();
#line 85
}
#line 85
# 236 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart1P.nc"
static inline   void HplMsp430Usart1P$Usart$disableUartRx(void)
#line 236
{
  HplMsp430Usart1P$ME2 &= ~(1 << 4);
  HplMsp430Usart1P$URXD$selectIOFunc();
}

# 54 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P37*/HplMsp430GeneralIOP$23$IO$selectModuleFunc(void)
#line 54
{
  /* atomic removed: atomic calls only */
#line 54
  * (volatile uint8_t *)27U |= 0x01 << 7;
}

# 78 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void HplMsp430Usart1P$URXD$selectModuleFunc(void){
#line 78
  /*HplMsp430GeneralIOC.P37*/HplMsp430GeneralIOP$23$IO$selectModuleFunc();
#line 78
}
#line 78
# 231 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart1P.nc"
static inline   void HplMsp430Usart1P$Usart$enableUartRx(void)
#line 231
{
  HplMsp430Usart1P$URXD$selectModuleFunc();
  HplMsp430Usart1P$ME2 |= 1 << 4;
}

# 56 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P36*/HplMsp430GeneralIOP$22$IO$selectIOFunc(void)
#line 56
{
  /* atomic removed: atomic calls only */
#line 56
  * (volatile uint8_t *)27U &= ~(0x01 << 6);
}

# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void HplMsp430Usart1P$UTXD$selectIOFunc(void){
#line 85
  /*HplMsp430GeneralIOC.P36*/HplMsp430GeneralIOP$22$IO$selectIOFunc();
#line 85
}
#line 85
# 225 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart1P.nc"
static inline   void HplMsp430Usart1P$Usart$disableUartTx(void)
#line 225
{
  HplMsp430Usart1P$ME2 &= ~(1 << 5);
  HplMsp430Usart1P$UTXD$selectIOFunc();
}

#line 203
static inline   void HplMsp430Usart1P$Usart$enableUart(void)
#line 203
{
  /* atomic removed: atomic calls only */
#line 204
  {
    HplMsp430Usart1P$UTXD$selectModuleFunc();
    HplMsp430Usart1P$URXD$selectModuleFunc();
  }
  HplMsp430Usart1P$ME2 |= (1 << 5) | (1 << 4);
}

#line 151
static inline   void HplMsp430Usart1P$Usart$setUmctl(uint8_t control)
#line 151
{
  U1MCTL = control;
}

#line 140
static inline   void HplMsp430Usart1P$Usart$setUbr(uint16_t control)
#line 140
{
  /* atomic removed: atomic calls only */
#line 141
  {
    U1BR0 = control & 0x00FF;
    U1BR1 = (control >> 8) & 0x00FF;
  }
}

#line 283
static inline void HplMsp430Usart1P$configUart(msp430_uart_union_config_t *config)
#line 283
{

  U1CTL = (config->uartRegisters.uctl & ~0x04) | 0x01;
  HplMsp430Usart1P$U1TCTL = config->uartRegisters.utctl;
  HplMsp430Usart1P$U1RCTL = config->uartRegisters.urctl;

  HplMsp430Usart1P$Usart$setUbr(config->uartRegisters.ubr);
  HplMsp430Usart1P$Usart$setUmctl(config->uartRegisters.umctl);
}

static inline   void HplMsp430Usart1P$Usart$setModeUart(msp430_uart_union_config_t *config)
#line 293
{

  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 295
    {
      HplMsp430Usart1P$Usart$resetUsart(TRUE);
      HplMsp430Usart1P$Usart$disableSpi();
      HplMsp430Usart1P$configUart(config);
      if (config->uartConfig.utxe == 1 && config->uartConfig.urxe == 1) {
          HplMsp430Usart1P$Usart$enableUart();
        }
      else {
#line 301
        if (config->uartConfig.utxe == 0 && config->uartConfig.urxe == 1) {
            HplMsp430Usart1P$Usart$disableUartTx();
            HplMsp430Usart1P$Usart$enableUartRx();
          }
        else {
#line 304
          if (config->uartConfig.utxe == 1 && config->uartConfig.urxe == 0) {
              HplMsp430Usart1P$Usart$disableUartRx();
              HplMsp430Usart1P$Usart$enableUartTx();
            }
          else 
#line 307
            {
              HplMsp430Usart1P$Usart$disableUart();
            }
          }
        }
#line 310
      HplMsp430Usart1P$Usart$resetUsart(FALSE);
      HplMsp430Usart1P$Usart$clrIntr();
      HplMsp430Usart1P$Usart$disableIntr();
    }
#line 313
    __nesc_atomic_end(__nesc_atomic); }

  return;
}

# 174 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
inline static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$setModeUart(msp430_uart_union_config_t *arg_0x40a920a8){
#line 174
  HplMsp430Usart1P$Usart$setModeUart(arg_0x40a920a8);
#line 174
}
#line 174
# 56 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P51*/HplMsp430GeneralIOP$33$IO$selectIOFunc(void)
#line 56
{
  /* atomic removed: atomic calls only */
#line 56
  * (volatile uint8_t *)51U &= ~(0x01 << 1);
}

# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void HplMsp430Usart1P$SIMO$selectIOFunc(void){
#line 85
  /*HplMsp430GeneralIOC.P51*/HplMsp430GeneralIOP$33$IO$selectIOFunc();
#line 85
}
#line 85
# 56 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P52*/HplMsp430GeneralIOP$34$IO$selectIOFunc(void)
#line 56
{
  /* atomic removed: atomic calls only */
#line 56
  * (volatile uint8_t *)51U &= ~(0x01 << 2);
}

# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void HplMsp430Usart1P$SOMI$selectIOFunc(void){
#line 85
  /*HplMsp430GeneralIOC.P52*/HplMsp430GeneralIOP$34$IO$selectIOFunc();
#line 85
}
#line 85
# 56 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P53*/HplMsp430GeneralIOP$35$IO$selectIOFunc(void)
#line 56
{
  /* atomic removed: atomic calls only */
#line 56
  * (volatile uint8_t *)51U &= ~(0x01 << 3);
}

# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void HplMsp430Usart1P$UCLK$selectIOFunc(void){
#line 85
  /*HplMsp430GeneralIOC.P53*/HplMsp430GeneralIOP$35$IO$selectIOFunc();
#line 85
}
#line 85
# 377 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart1P.nc"
static inline   void HplMsp430Usart1P$Usart$enableIntr(void)
#line 377
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 378
    {
      HplMsp430Usart1P$IFG2 &= ~((1 << 5) | (1 << 4));
      HplMsp430Usart1P$IE2 |= (1 << 5) | (1 << 4);
    }
#line 381
    __nesc_atomic_end(__nesc_atomic); }
}

# 182 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
inline static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$enableIntr(void){
#line 182
  HplMsp430Usart1P$Usart$enableIntr();
#line 182
}
#line 182
# 181 "/opt/tinyos-2.x/tos/system/AMQueueImplP.nc"
static inline  void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$AMSend$sendDone(am_id_t id, message_t *msg, error_t err)
#line 181
{





  if (/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current >= 2) {
      return;
    }
  if (/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$queue[/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current].msg == msg) {
      /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$sendDone(/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current, msg, err);
    }
  else {
      ;
    }
}

# 99 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
inline static  void /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMSend$sendDone(am_id_t arg_0x40c00c98, message_t *arg_0x40636ea0, error_t arg_0x40635030){
#line 99
  /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$AMSend$sendDone(arg_0x40c00c98, arg_0x40636ea0, arg_0x40635030);
#line 99
}
#line 99
# 81 "/opt/tinyos-2.x/tos/lib/serial/SerialActiveMessageP.nc"
static inline  void /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$SubSend$sendDone(message_t *msg, error_t result)
#line 81
{
  /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMSend$sendDone(/*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$type(msg), msg, result);
}

# 362 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
static inline   void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Send$default$sendDone(uart_id_t idxxx, message_t *msg, error_t error)
#line 362
{
  return;
}

# 89 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
inline static  void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Send$sendDone(uart_id_t arg_0x40cafa48, message_t *arg_0x40bf6dd8, error_t arg_0x40bf5010){
#line 89
  switch (arg_0x40cafa48) {
#line 89
    case TOS_SERIAL_ACTIVE_MESSAGE_ID:
#line 89
      /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$SubSend$sendDone(arg_0x40bf6dd8, arg_0x40bf5010);
#line 89
      break;
#line 89
    default:
#line 89
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Send$default$sendDone(arg_0x40cafa48, arg_0x40bf6dd8, arg_0x40bf5010);
#line 89
      break;
#line 89
    }
#line 89
}
#line 89
# 147 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
static inline  void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$signalSendDone$runTask(void)
#line 147
{
  error_t error;

  /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendState = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SEND_STATE_IDLE;
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 151
    error = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendError;
#line 151
    __nesc_atomic_end(__nesc_atomic); }

  if (/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendCancelled) {
#line 153
    error = ECANCEL;
    }
#line 154
  /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Send$sendDone(/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendId, (message_t *)/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendBuffer, error);
}

#line 201
static inline void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$unlockBuffer(uint8_t which)
#line 201
{
  if (which) {
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState.bufOneLocked = 0;
    }
  else {
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState.bufZeroLocked = 0;
    }
}

# 39 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$HplGeneralIO$clr(void){
#line 39
  /*HplMsp430GeneralIOC.P54*/HplMsp430GeneralIOP$36$IO$clr();
#line 39
}
#line 39
# 38 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$GeneralIO$clr(void)
#line 38
{
#line 38
  /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$HplGeneralIO$clr();
}

# 30 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
inline static   void LedsP$Led0$clr(void){
#line 30
  /*PlatformLedsC.Led0Impl*/Msp430GpioC$0$GeneralIO$clr();
#line 30
}
#line 30
# 63 "/opt/tinyos-2.x/tos/system/LedsP.nc"
static inline   void LedsP$Leds$led0On(void)
#line 63
{
  LedsP$Led0$clr();
  ;
#line 65
  ;
}

# 45 "/opt/tinyos-2.x/tos/interfaces/Leds.nc"
inline static   void TraceRetrieverP$Leds$led0On(void){
#line 45
  LedsP$Leds$led0On();
#line 45
}
#line 45
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
inline static   error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$startTask$postTask(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = SchedulerBasicP$TaskBasic$postTask(/*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$startTask);
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 80 "/opt/tinyos-2.x/tos/lib/power/DeferredPowerManagerP.nc"
static inline   void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$ResourceDefaultOwner$requested(void)
#line 80
{
  if (/*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$stopping == FALSE) {
      /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$stopTimer = TRUE;
      /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$startTask$postTask();
    }
  else {
#line 85
    { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 85
      /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$requested = TRUE;
#line 85
      __nesc_atomic_end(__nesc_atomic); }
    }
}

# 73 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
inline static   void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceDefaultOwner$requested(void){
#line 73
  /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$ResourceDefaultOwner$requested();
#line 73
}
#line 73
# 54 "/opt/tinyos-2.x/tos/system/FcfsResourceQueueC.nc"
static inline   bool /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$FcfsQueue$isEnqueued(resource_client_id_t id)
#line 54
{
  return /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$resQ[id] != /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$NO_ENTRY || /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$qTail == id;
}

#line 72
static inline   error_t /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$FcfsQueue$enqueue(resource_client_id_t id)
#line 72
{
  /* atomic removed: atomic calls only */
#line 73
  {
    if (!/*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$FcfsQueue$isEnqueued(id)) {
        if (/*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$qHead == /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$NO_ENTRY) {
          /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$qHead = id;
          }
        else {
#line 78
          /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$resQ[/*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$qTail] = id;
          }
#line 79
        /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$qTail = id;
        {
          unsigned char __nesc_temp = 
#line 80
          SUCCESS;

#line 80
          return __nesc_temp;
        }
      }
#line 82
    {
      unsigned char __nesc_temp = 
#line 82
      EBUSY;

#line 82
      return __nesc_temp;
    }
  }
}

# 69 "/opt/tinyos-2.x/tos/interfaces/ResourceQueue.nc"
inline static   error_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Queue$enqueue(resource_client_id_t arg_0x4088c458){
#line 69
  unsigned char result;
#line 69

#line 69
  result = /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$FcfsQueue$enqueue(arg_0x4088c458);
#line 69

#line 69
  return result;
#line 69
}
#line 69
# 196 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline    void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceRequested$default$requested(uint8_t id)
#line 196
{
}

# 43 "/opt/tinyos-2.x/tos/interfaces/ResourceRequested.nc"
inline static   void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceRequested$requested(uint8_t arg_0x4089e830){
#line 43
    /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceRequested$default$requested(arg_0x4089e830);
#line 43
}
#line 43
# 77 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline   error_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Resource$request(uint8_t id)
#line 77
{
  /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceRequested$requested(/*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$resId);
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 79
    {
      if (/*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$state == /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$RES_CONTROLLED) {
          /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$state = /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$RES_GRANTING;
          /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$reqResId = id;
        }
      else {
          unsigned char __nesc_temp = 
#line 84
          /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Queue$enqueue(id);

          {
#line 84
            __nesc_atomic_end(__nesc_atomic); 
#line 84
            return __nesc_temp;
          }
        }
    }
#line 87
    __nesc_atomic_end(__nesc_atomic); }
#line 86
  /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceDefaultOwner$requested();
  return SUCCESS;
}

# 78 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static   error_t Stm25pSectorP$Stm25pResource$request(uint8_t arg_0x40828d68){
#line 78
  unsigned char result;
#line 78

#line 78
  result = /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Resource$request(arg_0x40828d68);
#line 78

#line 78
  return result;
#line 78
}
#line 78
# 102 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline   error_t Stm25pSectorP$ClientResource$request(uint8_t id)
#line 102
{
  return Stm25pSectorP$Stm25pResource$request(id);
}

# 526 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline    error_t Stm25pLogP$ClientResource$default$request(uint8_t id)
#line 526
{
#line 526
  return FAIL;
}

# 78 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static   error_t Stm25pLogP$ClientResource$request(uint8_t arg_0x407a8360){
#line 78
  unsigned char result;
#line 78

#line 78
  switch (arg_0x407a8360) {
#line 78
    case /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$LOG_ID:
#line 78
      result = Stm25pSectorP$ClientResource$request(/*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$VOLUME_ID);
#line 78
      break;
#line 78
    default:
#line 78
      result = Stm25pLogP$ClientResource$default$request(arg_0x407a8360);
#line 78
      break;
#line 78
    }
#line 78

#line 78
  return result;
#line 78
}
#line 78
# 190 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline error_t Stm25pLogP$newRequest(uint8_t client)
#line 190
{

  if (Stm25pLogP$m_log_state[client].req != Stm25pLogP$S_IDLE) {
    return FAIL;
    }
  Stm25pLogP$ClientResource$request(client);
  Stm25pLogP$m_log_state[client] = Stm25pLogP$m_req;

  return SUCCESS;
}

#line 118
static inline  error_t Stm25pLogP$Read$read(uint8_t id, void *buf, storage_len_t len)
#line 118
{

  Stm25pLogP$m_req.req = Stm25pLogP$S_READ;
  Stm25pLogP$m_req.buf = buf;
  Stm25pLogP$m_req.len = len;
  Stm25pLogP$m_len = len;
  return Stm25pLogP$newRequest(id);
}

# 63 "/opt/tinyos-2.x/tos/interfaces/LogRead.nc"
inline static  error_t TraceRetrieverP$LogRead$read(void *arg_0x40614e10, storage_len_t arg_0x40613010){
#line 63
  unsigned char result;
#line 63

#line 63
  result = Stm25pLogP$Read$read(/*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$LOG_ID, arg_0x40614e10, arg_0x40613010);
#line 63

#line 63
  return result;
#line 63
}
#line 63
# 83 "TraceRetrieverP.nc"
static __inline void TraceRetrieverP$readNextLog(void)
#line 83
{
  TraceRetrieverP$reading = TraceRetrieverP$LogRead$read(&TraceRetrieverP$traceLogEntry, 
  sizeof(TraceLogEntry )) == SUCCESS;
  if (!TraceRetrieverP$reading) {
      TraceRetrieverP$Leds$led0On();
    }
}

#line 74
static inline  message_t *TraceRetrieverP$UARTReceive$receive(message_t *bufPtr, void *payload, 
uint8_t length)
#line 75
{
  if (!TraceRetrieverP$reading) {
      TraceRetrieverP$readNextLog();
    }
  return bufPtr;
}

# 89 "/opt/tinyos-2.x/tos/lib/serial/SerialActiveMessageP.nc"
static inline   message_t */*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Receive$default$receive(uint8_t id, message_t *msg, void *payload, uint8_t len)
#line 89
{
  return msg;
}

# 67 "/opt/tinyos-2.x/tos/interfaces/Receive.nc"
inline static  message_t */*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Receive$receive(am_id_t arg_0x40bff650, message_t *arg_0x406437d0, void *arg_0x40643968, uint8_t arg_0x40643ae8){
#line 67
  nx_struct message_t *result;
#line 67

#line 67
  switch (arg_0x40bff650) {
#line 67
    case 29:
#line 67
      result = TraceRetrieverP$UARTReceive$receive(arg_0x406437d0, arg_0x40643968, arg_0x40643ae8);
#line 67
      break;
#line 67
    default:
#line 67
      result = /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Receive$default$receive(arg_0x40bff650, arg_0x406437d0, arg_0x40643968, arg_0x40643ae8);
#line 67
      break;
#line 67
    }
#line 67

#line 67
  return result;
#line 67
}
#line 67
# 93 "/opt/tinyos-2.x/tos/lib/serial/SerialActiveMessageP.nc"
static inline  message_t */*SerialActiveMessageC.AM*/SerialActiveMessageP$0$SubReceive$receive(message_t *msg, void *payload, uint8_t len)
#line 93
{
  return /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Receive$receive(/*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$type(msg), msg, msg->data, len);
}

# 357 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
static inline   message_t */*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Receive$default$receive(uart_id_t idxxx, message_t *msg, 
void *payload, 
uint8_t len)
#line 359
{
  return msg;
}

# 67 "/opt/tinyos-2.x/tos/interfaces/Receive.nc"
inline static  message_t */*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Receive$receive(uart_id_t arg_0x40caf4a8, message_t *arg_0x406437d0, void *arg_0x40643968, uint8_t arg_0x40643ae8){
#line 67
  nx_struct message_t *result;
#line 67

#line 67
  switch (arg_0x40caf4a8) {
#line 67
    case TOS_SERIAL_ACTIVE_MESSAGE_ID:
#line 67
      result = /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$SubReceive$receive(arg_0x406437d0, arg_0x40643968, arg_0x40643ae8);
#line 67
      break;
#line 67
    default:
#line 67
      result = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Receive$default$receive(arg_0x40caf4a8, arg_0x406437d0, arg_0x40643968, arg_0x40643ae8);
#line 67
      break;
#line 67
    }
#line 67

#line 67
  return result;
#line 67
}
#line 67
# 46 "/opt/tinyos-2.x/tos/lib/serial/SerialPacketInfoActiveMessageP.nc"
static inline   uint8_t SerialPacketInfoActiveMessageP$Info$upperLength(message_t *msg, uint8_t dataLinkLen)
#line 46
{
  return dataLinkLen - sizeof(serial_header_t );
}

# 351 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
static inline    uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$default$upperLength(uart_id_t id, message_t *msg, 
uint8_t dataLinkLen)
#line 352
{
  return 0;
}

# 31 "/opt/tinyos-2.x/tos/lib/serial/SerialPacketInfo.nc"
inline static   uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$upperLength(uart_id_t arg_0x40cae450, message_t *arg_0x40c197f0, uint8_t arg_0x40c19978){
#line 31
  unsigned char result;
#line 31

#line 31
  switch (arg_0x40cae450) {
#line 31
    case TOS_SERIAL_ACTIVE_MESSAGE_ID:
#line 31
      result = SerialPacketInfoActiveMessageP$Info$upperLength(arg_0x40c197f0, arg_0x40c19978);
#line 31
      break;
#line 31
    default:
#line 31
      result = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$default$upperLength(arg_0x40cae450, arg_0x40c197f0, arg_0x40c19978);
#line 31
      break;
#line 31
    }
#line 31

#line 31
  return result;
#line 31
}
#line 31
# 264 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
static inline  void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTask$runTask(void)
#line 264
{
  uart_id_t myType;
  message_t *myBuf;
  uint8_t mySize;
  uint8_t myWhich;

#line 269
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 269
    {
      myType = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTaskType;
      myBuf = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTaskBuf;
      mySize = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTaskSize;
      myWhich = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTaskWhich;
    }
#line 274
    __nesc_atomic_end(__nesc_atomic); }
  mySize -= /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$offset(myType);
  mySize = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$upperLength(myType, myBuf, mySize);
  myBuf = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$Receive$receive(myType, myBuf, myBuf, mySize);
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 278
    {
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$messagePtrs[myWhich] = myBuf;
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$unlockBuffer(myWhich);
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTaskPending = FALSE;
    }
#line 282
    __nesc_atomic_end(__nesc_atomic); }
}

# 130 "TraceRetrieverP.nc"
static inline  void TraceRetrieverP$AMControl$stopDone(error_t err)
#line 130
{
}

# 117 "/opt/tinyos-2.x/tos/interfaces/SplitControl.nc"
inline static  void SerialP$SplitControl$stopDone(error_t arg_0x4061f1e0){
#line 117
  TraceRetrieverP$AMControl$stopDone(arg_0x4061f1e0);
#line 117
}
#line 117
# 97 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
inline static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$resetUsart(bool arg_0x40a98a30){
#line 97
  HplMsp430Usart1P$Usart$resetUsart(arg_0x40a98a30);
#line 97
}
#line 97
#line 128
inline static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$disableUart(void){
#line 128
  HplMsp430Usart1P$Usart$disableUart();
#line 128
}
#line 128
#line 179
inline static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$disableIntr(void){
#line 179
  HplMsp430Usart1P$Usart$disableIntr();
#line 179
}
#line 179
# 89 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
static inline   void /*Msp430Uart1P.UartP*/Msp430UartP$0$ResourceConfigure$unconfigure(uint8_t id)
#line 89
{
  /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$resetUsart(TRUE);
  /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$disableIntr();
  /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$disableUart();
  /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$resetUsart(FALSE);
}

# 210 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline    void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceConfigure$default$unconfigure(uint8_t id)
#line 210
{
}

# 55 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
inline static   void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceConfigure$unconfigure(uint8_t arg_0x4089c9b8){
#line 55
  switch (arg_0x4089c9b8) {
#line 55
    case /*PlatformSerialC.UartC.UsartC*/Msp430Usart1C$0$CLIENT_ID:
#line 55
      /*Msp430Uart1P.UartP*/Msp430UartP$0$ResourceConfigure$unconfigure(/*PlatformSerialC.UartC*/Msp430Uart1C$0$CLIENT_ID);
#line 55
      break;
#line 55
    default:
#line 55
      /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceConfigure$default$unconfigure(arg_0x4089c9b8);
#line 55
      break;
#line 55
    }
#line 55
}
#line 55
# 109 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart1P.nc"
static inline   error_t HplMsp430Usart1P$AsyncStdControl$stop(void)
#line 109
{
  HplMsp430Usart1P$Usart$disableSpi();
  HplMsp430Usart1P$Usart$disableUart();
  return SUCCESS;
}

# 84 "/opt/tinyos-2.x/tos/interfaces/AsyncStdControl.nc"
inline static   error_t /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$AsyncStdControl$stop(void){
#line 84
  unsigned char result;
#line 84

#line 84
  result = HplMsp430Usart1P$AsyncStdControl$stop();
#line 84

#line 84
  return result;
#line 84
}
#line 84
# 74 "/opt/tinyos-2.x/tos/lib/power/AsyncPowerManagerP.nc"
static inline    void /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$PowerDownCleanup$default$cleanup(void)
#line 74
{
}

# 52 "/opt/tinyos-2.x/tos/lib/power/PowerDownCleanup.nc"
inline static   void /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$PowerDownCleanup$cleanup(void){
#line 52
  /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$PowerDownCleanup$default$cleanup();
#line 52
}
#line 52
# 69 "/opt/tinyos-2.x/tos/lib/power/AsyncPowerManagerP.nc"
static inline   void /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$ResourceDefaultOwner$granted(void)
#line 69
{
  /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$PowerDownCleanup$cleanup();
  /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$AsyncStdControl$stop();
}

# 46 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
inline static   void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceDefaultOwner$granted(void){
#line 46
  /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$ResourceDefaultOwner$granted();
#line 46
}
#line 46
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
inline static   error_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$grantedTask$postTask(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = SchedulerBasicP$TaskBasic$postTask(/*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$grantedTask);
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 58 "/opt/tinyos-2.x/tos/system/FcfsResourceQueueC.nc"
static inline   resource_client_id_t /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$FcfsQueue$dequeue(void)
#line 58
{
  /* atomic removed: atomic calls only */
#line 59
  {
    if (/*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$qHead != /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$NO_ENTRY) {
        uint8_t id = /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$qHead;

#line 62
        /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$qHead = /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$resQ[/*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$qHead];
        if (/*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$qHead == /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$NO_ENTRY) {
          /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$qTail = /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$NO_ENTRY;
          }
#line 65
        /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$resQ[id] = /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$NO_ENTRY;
        {
          unsigned char __nesc_temp = 
#line 66
          id;

#line 66
          return __nesc_temp;
        }
      }
#line 68
    {
      unsigned char __nesc_temp = 
#line 68
      /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$NO_ENTRY;

#line 68
      return __nesc_temp;
    }
  }
}

# 60 "/opt/tinyos-2.x/tos/interfaces/ResourceQueue.nc"
inline static   resource_client_id_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Queue$dequeue(void){
#line 60
  unsigned char result;
#line 60

#line 60
  result = /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$FcfsQueue$dequeue();
#line 60

#line 60
  return result;
#line 60
}
#line 60
# 50 "/opt/tinyos-2.x/tos/system/FcfsResourceQueueC.nc"
static inline   bool /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$FcfsQueue$isEmpty(void)
#line 50
{
  return /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$qHead == /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$NO_ENTRY;
}

# 43 "/opt/tinyos-2.x/tos/interfaces/ResourceQueue.nc"
inline static   bool /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Queue$isEmpty(void){
#line 43
  unsigned char result;
#line 43

#line 43
  result = /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$FcfsQueue$isEmpty();
#line 43

#line 43
  return result;
#line 43
}
#line 43
# 108 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline   error_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Resource$release(uint8_t id)
#line 108
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 109
    {
      if (/*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$state == /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$RES_BUSY && /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$resId == id) {
          if (/*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Queue$isEmpty() == FALSE) {
              /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$reqResId = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Queue$dequeue();
              /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$state = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$RES_GRANTING;
              /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$grantedTask$postTask();
            }
          else {
              /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$resId = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$default_owner_id;
              /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$state = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$RES_CONTROLLED;
              /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceDefaultOwner$granted();
            }
          /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceConfigure$unconfigure(id);
        }
    }
#line 123
    __nesc_atomic_end(__nesc_atomic); }
  return FAIL;
}

# 191 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
static inline    error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$default$release(uint8_t id)
#line 191
{
#line 191
  return FAIL;
}

# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static   error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$release(uint8_t arg_0x40d26420){
#line 110
  unsigned char result;
#line 110

#line 110
  switch (arg_0x40d26420) {
#line 110
    case /*PlatformSerialC.UartC*/Msp430Uart1C$0$CLIENT_ID:
#line 110
      result = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Resource$release(/*PlatformSerialC.UartC.UsartC*/Msp430Usart1C$0$CLIENT_ID);
#line 110
      break;
#line 110
    default:
#line 110
      result = /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$default$release(arg_0x40d26420);
#line 110
      break;
#line 110
    }
#line 110

#line 110
  return result;
#line 110
}
#line 110
# 76 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
static inline   error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$Resource$release(uint8_t id)
#line 76
{
  if (/*Msp430Uart1P.UartP*/Msp430UartP$0$m_rx_buf || /*Msp430Uart1P.UartP*/Msp430UartP$0$m_tx_buf) {
    return EBUSY;
    }
#line 79
  return /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$release(id);
}

# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static   error_t TelosSerialP$Resource$release(void){
#line 110
  unsigned char result;
#line 110

#line 110
  result = /*Msp430Uart1P.UartP*/Msp430UartP$0$Resource$release(/*PlatformSerialC.UartC*/Msp430Uart1C$0$CLIENT_ID);
#line 110

#line 110
  return result;
#line 110
}
#line 110
# 13 "/opt/tinyos-2.x/tos/platforms/telosa/TelosSerialP.nc"
static inline  error_t TelosSerialP$StdControl$stop(void)
#line 13
{
  TelosSerialP$Resource$release();
  return SUCCESS;
}

# 84 "/opt/tinyos-2.x/tos/interfaces/StdControl.nc"
inline static  error_t SerialP$SerialControl$stop(void){
#line 84
  unsigned char result;
#line 84

#line 84
  result = TelosSerialP$StdControl$stop();
#line 84

#line 84
  return result;
#line 84
}
#line 84
# 330 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static inline  void SerialP$SerialFlush$flushDone(void)
#line 330
{
  SerialP$SerialControl$stop();
  SerialP$SplitControl$stopDone(SUCCESS);
}

static inline  void SerialP$defaultSerialFlushTask$runTask(void)
#line 335
{
  SerialP$SerialFlush$flushDone();
}

# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
inline static   error_t SerialP$defaultSerialFlushTask$postTask(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = SchedulerBasicP$TaskBasic$postTask(SerialP$defaultSerialFlushTask);
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 338 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static inline   void SerialP$SerialFlush$default$flush(void)
#line 338
{
  SerialP$defaultSerialFlushTask$postTask();
}

# 38 "/opt/tinyos-2.x/tos/lib/serial/SerialFlush.nc"
inline static  void SerialP$SerialFlush$flush(void){
#line 38
  SerialP$SerialFlush$default$flush();
#line 38
}
#line 38
# 326 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static inline  void SerialP$stopDoneTask$runTask(void)
#line 326
{
  SerialP$SerialFlush$flush();
}

# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
inline static   error_t SerialP$startDoneTask$postTask(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = SchedulerBasicP$TaskBasic$postTask(SerialP$startDoneTask);
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 342 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static inline  error_t SerialP$SplitControl$start(void)
#line 342
{
  SerialP$startDoneTask$postTask();
  return SUCCESS;
}

# 83 "/opt/tinyos-2.x/tos/interfaces/SplitControl.nc"
inline static  error_t TraceRetrieverP$AMControl$start(void){
#line 83
  unsigned char result;
#line 83

#line 83
  result = SerialP$SplitControl$start();
#line 83

#line 83
  return result;
#line 83
}
#line 83
# 65 "TraceRetrieverP.nc"
static inline  void TraceRetrieverP$AMControl$startDone(error_t err)
#line 65
{
  if (err == SUCCESS) {
      TraceRetrieverP$reading = FALSE;
    }
  else 
#line 68
    {
      TraceRetrieverP$AMControl$start();
    }
}

# 92 "/opt/tinyos-2.x/tos/interfaces/SplitControl.nc"
inline static  void SerialP$SplitControl$startDone(error_t arg_0x40620638){
#line 92
  TraceRetrieverP$AMControl$startDone(arg_0x40620638);
#line 92
}
#line 92
# 127 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline   error_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceDefaultOwner$release(void)
#line 127
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 128
    {
      if (/*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$resId == /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$default_owner_id) {
          if (/*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$state == /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$RES_GRANTING) {
              /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$grantedTask$postTask();
              {
                unsigned char __nesc_temp = 
#line 132
                SUCCESS;

                {
#line 132
                  __nesc_atomic_end(__nesc_atomic); 
#line 132
                  return __nesc_temp;
                }
              }
            }
          else {
#line 134
            if (/*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$state == /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$RES_IMM_GRANTING) {
                /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$resId = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$reqResId;
                /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$state = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$RES_BUSY;
                {
                  unsigned char __nesc_temp = 
#line 137
                  SUCCESS;

                  {
#line 137
                    __nesc_atomic_end(__nesc_atomic); 
#line 137
                    return __nesc_temp;
                  }
                }
              }
            }
        }
    }
#line 143
    __nesc_atomic_end(__nesc_atomic); }
#line 141
  return FAIL;
}

# 56 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
inline static   error_t /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$ResourceDefaultOwner$release(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceDefaultOwner$release();
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 105 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart1P.nc"
static inline   error_t HplMsp430Usart1P$AsyncStdControl$start(void)
#line 105
{
  return SUCCESS;
}

# 74 "/opt/tinyos-2.x/tos/interfaces/AsyncStdControl.nc"
inline static   error_t /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$AsyncStdControl$start(void){
#line 74
  unsigned char result;
#line 74

#line 74
  result = HplMsp430Usart1P$AsyncStdControl$start();
#line 74

#line 74
  return result;
#line 74
}
#line 74
# 64 "/opt/tinyos-2.x/tos/lib/power/AsyncPowerManagerP.nc"
static inline   void /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$ResourceDefaultOwner$immediateRequested(void)
#line 64
{
  /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$AsyncStdControl$start();
  /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$ResourceDefaultOwner$release();
}

# 81 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
inline static   void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceDefaultOwner$immediateRequested(void){
#line 81
  /*Msp430UsartShare1P.PowerManagerC.PowerManager*/AsyncPowerManagerP$0$ResourceDefaultOwner$immediateRequested();
#line 81
}
#line 81
# 198 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline    void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceRequested$default$immediateRequested(uint8_t id)
#line 198
{
}

# 51 "/opt/tinyos-2.x/tos/interfaces/ResourceRequested.nc"
inline static   void /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceRequested$immediateRequested(uint8_t arg_0x4089e830){
#line 51
    /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceRequested$default$immediateRequested(arg_0x4089e830);
#line 51
}
#line 51
# 90 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline   error_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Resource$immediateRequest(uint8_t id)
#line 90
{
  /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceRequested$immediateRequested(/*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$resId);
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 92
    {
      if (/*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$state == /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$RES_CONTROLLED) {
          /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$state = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$RES_IMM_GRANTING;
          /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$reqResId = id;
        }
      else {
          unsigned char __nesc_temp = 
#line 97
          FAIL;

          {
#line 97
            __nesc_atomic_end(__nesc_atomic); 
#line 97
            return __nesc_temp;
          }
        }
    }
#line 100
    __nesc_atomic_end(__nesc_atomic); }
#line 99
  /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceDefaultOwner$immediateRequested();
  if (/*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$resId == id) {
      /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ResourceConfigure$configure(/*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$resId);
      return SUCCESS;
    }
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 104
    /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$state = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$RES_CONTROLLED;
#line 104
    __nesc_atomic_end(__nesc_atomic); }
  return FAIL;
}

# 190 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
static inline    error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$default$immediateRequest(uint8_t id)
#line 190
{
#line 190
  return FAIL;
}

# 87 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static   error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$immediateRequest(uint8_t arg_0x40d26420){
#line 87
  unsigned char result;
#line 87

#line 87
  switch (arg_0x40d26420) {
#line 87
    case /*PlatformSerialC.UartC*/Msp430Uart1C$0$CLIENT_ID:
#line 87
      result = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$Resource$immediateRequest(/*PlatformSerialC.UartC.UsartC*/Msp430Usart1C$0$CLIENT_ID);
#line 87
      break;
#line 87
    default:
#line 87
      result = /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$default$immediateRequest(arg_0x40d26420);
#line 87
      break;
#line 87
    }
#line 87

#line 87
  return result;
#line 87
}
#line 87
# 64 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
static inline   error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$Resource$immediateRequest(uint8_t id)
#line 64
{
  return /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartResource$immediateRequest(id);
}

# 87 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static   error_t TelosSerialP$Resource$immediateRequest(void){
#line 87
  unsigned char result;
#line 87

#line 87
  result = /*Msp430Uart1P.UartP*/Msp430UartP$0$Resource$immediateRequest(/*PlatformSerialC.UartC*/Msp430Uart1C$0$CLIENT_ID);
#line 87

#line 87
  return result;
#line 87
}
#line 87
# 10 "/opt/tinyos-2.x/tos/platforms/telosa/TelosSerialP.nc"
static inline  error_t TelosSerialP$StdControl$start(void)
#line 10
{
  return TelosSerialP$Resource$immediateRequest();
}

# 74 "/opt/tinyos-2.x/tos/interfaces/StdControl.nc"
inline static  error_t SerialP$SerialControl$start(void){
#line 74
  unsigned char result;
#line 74

#line 74
  result = TelosSerialP$StdControl$start();
#line 74

#line 74
  return result;
#line 74
}
#line 74
# 320 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static inline  void SerialP$startDoneTask$runTask(void)
#line 320
{
  SerialP$SerialControl$start();
  SerialP$SplitControl$startDone(SUCCESS);
}

# 45 "/opt/tinyos-2.x/tos/lib/serial/SerialFrameComm.nc"
inline static   error_t SerialP$SerialFrameComm$putDelimiter(void){
#line 45
  unsigned char result;
#line 45

#line 45
  result = HdlcTranslateC$SerialFrameComm$putDelimiter();
#line 45

#line 45
  return result;
#line 45
}
#line 45
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
inline static   error_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$signalSendDone$postTask(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = SchedulerBasicP$TaskBasic$postTask(/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$signalSendDone);
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 183 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
static inline   void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SendBytePacket$sendCompleted(error_t error)
#line 183
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 184
    /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendError = error;
#line 184
    __nesc_atomic_end(__nesc_atomic); }
  /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$signalSendDone$postTask();
}

# 80 "/opt/tinyos-2.x/tos/lib/serial/SendBytePacket.nc"
inline static   void SerialP$SendBytePacket$sendCompleted(error_t arg_0x40c38650){
#line 80
  /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SendBytePacket$sendCompleted(arg_0x40c38650);
#line 80
}
#line 80
# 242 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static __inline bool SerialP$ack_queue_is_empty(void)
#line 242
{
  bool ret;

#line 244
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 244
    ret = SerialP$ackQ.writePtr == SerialP$ackQ.readPtr;
#line 244
    __nesc_atomic_end(__nesc_atomic); }
  return ret;
}











static __inline uint8_t SerialP$ack_queue_top(void)
#line 258
{
  uint8_t tmp = 0;

  /* atomic removed: atomic calls only */
#line 260
  {
    if (!SerialP$ack_queue_is_empty()) {
        tmp = SerialP$ackQ.buf[SerialP$ackQ.readPtr];
      }
  }
  return tmp;
}

static inline uint8_t SerialP$ack_queue_pop(void)
#line 268
{
  uint8_t retval = 0;

#line 270
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 270
    {
      if (SerialP$ackQ.writePtr != SerialP$ackQ.readPtr) {
          retval = SerialP$ackQ.buf[SerialP$ackQ.readPtr];
          if (++ SerialP$ackQ.readPtr > SerialP$ACK_QUEUE_SIZE) {
#line 273
            SerialP$ackQ.readPtr = 0;
            }
        }
    }
#line 276
    __nesc_atomic_end(__nesc_atomic); }
#line 276
  return retval;
}

#line 539
static inline  void SerialP$RunTx$runTask(void)
#line 539
{
  uint8_t idle;
  uint8_t done;
  uint8_t fail;









  error_t result = SUCCESS;
  bool send_completed = FALSE;
  bool start_it = FALSE;

  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 556
    {
      SerialP$txPending = 0;
      idle = SerialP$txState == SerialP$TXSTATE_IDLE;
      done = SerialP$txState == SerialP$TXSTATE_FINISH;
      fail = SerialP$txState == SerialP$TXSTATE_ERROR;
      if (done || fail) {
          SerialP$txState = SerialP$TXSTATE_IDLE;
          SerialP$txBuf[SerialP$txIndex].state = SerialP$BUFFER_AVAILABLE;
        }
    }
#line 565
    __nesc_atomic_end(__nesc_atomic); }


  if (done || fail) {
      SerialP$txSeqno++;
      if (SerialP$txProto == SERIAL_PROTO_ACK) {
          SerialP$ack_queue_pop();
        }
      else {
          result = done ? SUCCESS : FAIL;
          send_completed = TRUE;
        }
      idle = TRUE;
    }


  if (idle) {
      bool goInactive;

#line 583
      { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 583
        goInactive = SerialP$offPending;
#line 583
        __nesc_atomic_end(__nesc_atomic); }
      if (goInactive) {
          { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 585
            SerialP$txState = SerialP$TXSTATE_INACTIVE;
#line 585
            __nesc_atomic_end(__nesc_atomic); }
        }
      else {

          uint8_t myAckState;
          uint8_t myDataState;

#line 591
          { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 591
            {
              myAckState = SerialP$txBuf[SerialP$TX_ACK_INDEX].state;
              myDataState = SerialP$txBuf[SerialP$TX_DATA_INDEX].state;
            }
#line 594
            __nesc_atomic_end(__nesc_atomic); }
          if (!SerialP$ack_queue_is_empty() && myAckState == SerialP$BUFFER_AVAILABLE) {
              { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 596
                {
                  SerialP$txBuf[SerialP$TX_ACK_INDEX].state = SerialP$BUFFER_COMPLETE;
                  SerialP$txBuf[SerialP$TX_ACK_INDEX].buf = SerialP$ack_queue_top();
                }
#line 599
                __nesc_atomic_end(__nesc_atomic); }
              SerialP$txProto = SERIAL_PROTO_ACK;
              SerialP$txIndex = SerialP$TX_ACK_INDEX;
              start_it = TRUE;
            }
          else {
#line 604
            if (myDataState == SerialP$BUFFER_FILLING || myDataState == SerialP$BUFFER_COMPLETE) {
                SerialP$txProto = SERIAL_PROTO_PACKET_NOACK;
                SerialP$txIndex = SerialP$TX_DATA_INDEX;
                start_it = TRUE;
              }
            else {
              }
            }
        }
    }
  else {
    }


  if (send_completed) {
      SerialP$SendBytePacket$sendCompleted(result);
    }

  if (SerialP$txState == SerialP$TXSTATE_INACTIVE) {
      SerialP$testOff();
      return;
    }

  if (start_it) {

      { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 629
        {
          SerialP$txCRC = 0;
          SerialP$txByteCnt = 0;
          SerialP$txState = SerialP$TXSTATE_PROTO;
        }
#line 633
        __nesc_atomic_end(__nesc_atomic); }
      if (SerialP$SerialFrameComm$putDelimiter() != SUCCESS) {
          { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 635
            SerialP$txState = SerialP$TXSTATE_ERROR;
#line 635
            __nesc_atomic_end(__nesc_atomic); }
          SerialP$MaybeScheduleTx();
        }
    }
}

# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
inline static   error_t SerialP$stopDoneTask$postTask(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = SchedulerBasicP$TaskBasic$postTask(SerialP$stopDoneTask);
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 284 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline   void Stm25pSectorP$ClientResource$default$granted(uint8_t id)
#line 284
{
}

# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static  void Stm25pSectorP$ClientResource$granted(uint8_t arg_0x4082a010){
#line 92
  switch (arg_0x4082a010) {
#line 92
    case /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$VOLUME_ID:
#line 92
      Stm25pLogP$ClientResource$granted(/*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$LOG_ID);
#line 92
      break;
#line 92
    default:
#line 92
      Stm25pSectorP$ClientResource$default$granted(arg_0x4082a010);
#line 92
      break;
#line 92
    }
#line 92
}
#line 92
# 83 "/opt/tinyos-2.x/tos/interfaces/SplitControl.nc"
inline static  error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$SplitControl$start(void){
#line 83
  unsigned char result;
#line 83

#line 83
  result = Stm25pSectorP$SplitControl$start();
#line 83

#line 83
  return result;
#line 83
}
#line 83
# 91 "/opt/tinyos-2.x/tos/lib/power/DeferredPowerManagerP.nc"
static inline   error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$StdControl$default$start(void)
#line 91
{
  return SUCCESS;
}

# 74 "/opt/tinyos-2.x/tos/interfaces/StdControl.nc"
inline static  error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$StdControl$start(void){
#line 74
  unsigned char result;
#line 74

#line 74
  result = /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$StdControl$default$start();
#line 74

#line 74
  return result;
#line 74
}
#line 74
# 116 "/opt/tinyos-2.x/tos/lib/power/DeferredPowerManagerP.nc"
static inline  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$SplitControl$stopDone(error_t error)
#line 116
{
  if (/*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$requested == TRUE) {
      /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$StdControl$start();
      /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$SplitControl$start();
    }
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 121
    {
      /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$requested = FALSE;
      /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$stopping = FALSE;
    }
#line 124
    __nesc_atomic_end(__nesc_atomic); }
}

# 117 "/opt/tinyos-2.x/tos/interfaces/SplitControl.nc"
inline static  void Stm25pSectorP$SplitControl$stopDone(error_t arg_0x4061f1e0){
#line 117
  /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$SplitControl$stopDone(arg_0x4061f1e0);
#line 117
}
#line 117
# 113 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
static inline    error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$default$release(uint8_t id)
#line 113
{
#line 113
  return FAIL;
}

# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$release(uint8_t arg_0x40a7de58){
#line 110
  unsigned char result;
#line 110

#line 110
  switch (arg_0x40a7de58) {
#line 110
    case /*HplStm25pSpiC.SpiC*/Msp430Spi0C$0$CLIENT_ID:
#line 110
      result = /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Resource$release(/*HplStm25pSpiC.SpiC.UsartC*/Msp430Usart0C$0$CLIENT_ID);
#line 110
      break;
#line 110
    default:
#line 110
      result = /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$default$release(arg_0x40a7de58);
#line 110
      break;
#line 110
    }
#line 110

#line 110
  return result;
#line 110
}
#line 110
# 80 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
static inline   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Resource$release(uint8_t id)
#line 80
{
  return /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$release(id);
}

# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static   error_t Stm25pSpiP$SpiResource$release(void){
#line 110
  unsigned char result;
#line 110

#line 110
  result = /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Resource$release(/*HplStm25pSpiC.SpiC*/Msp430Spi0C$0$CLIENT_ID);
#line 110

#line 110
  return result;
#line 110
}
#line 110
# 116 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpiP.nc"
static inline   error_t Stm25pSpiP$ClientResource$release(void)
#line 116
{
  return Stm25pSpiP$SpiResource$release();
}

# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static   error_t Stm25pSectorP$SpiResource$release(void){
#line 110
  unsigned char result;
#line 110

#line 110
  result = Stm25pSpiP$ClientResource$release();
#line 110

#line 110
  return result;
#line 110
}
#line 110
# 128 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpiP.nc"
static inline   error_t Stm25pSpiP$Spi$powerDown(void)
#line 128
{
  Stm25pSpiP$sendCmd(Stm25pSpiP$S_DEEP_SLEEP, 1);
  return SUCCESS;
}

# 47 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpi.nc"
inline static   error_t Stm25pSectorP$Spi$powerDown(void){
#line 47
  unsigned char result;
#line 47

#line 47
  result = Stm25pSpiP$Spi$powerDown();
#line 47

#line 47
  return result;
#line 47
}
#line 47
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
inline static   error_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$grantedTask$postTask(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = SchedulerBasicP$TaskBasic$postTask(/*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$grantedTask);
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 127 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline   error_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceDefaultOwner$release(void)
#line 127
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 128
    {
      if (/*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$resId == /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$default_owner_id) {
          if (/*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$state == /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$RES_GRANTING) {
              /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$grantedTask$postTask();
              {
                unsigned char __nesc_temp = 
#line 132
                SUCCESS;

                {
#line 132
                  __nesc_atomic_end(__nesc_atomic); 
#line 132
                  return __nesc_temp;
                }
              }
            }
          else {
#line 134
            if (/*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$state == /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$RES_IMM_GRANTING) {
                /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$resId = /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$reqResId;
                /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$state = /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$RES_BUSY;
                {
                  unsigned char __nesc_temp = 
#line 137
                  SUCCESS;

                  {
#line 137
                    __nesc_atomic_end(__nesc_atomic); 
#line 137
                    return __nesc_temp;
                  }
                }
              }
            }
        }
    }
#line 143
    __nesc_atomic_end(__nesc_atomic); }
#line 141
  return FAIL;
}

# 56 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
inline static   error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$ResourceDefaultOwner$release(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceDefaultOwner$release();
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 99 "/opt/tinyos-2.x/tos/lib/power/DeferredPowerManagerP.nc"
static inline  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$SplitControl$startDone(error_t error)
#line 99
{
  /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$ResourceDefaultOwner$release();
}

# 92 "/opt/tinyos-2.x/tos/interfaces/SplitControl.nc"
inline static  void Stm25pSectorP$SplitControl$startDone(error_t arg_0x40620638){
#line 92
  /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$SplitControl$startDone(arg_0x40620638);
#line 92
}
#line 92
# 133 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpiP.nc"
static inline   error_t Stm25pSpiP$Spi$powerUp(void)
#line 133
{
  Stm25pSpiP$sendCmd(Stm25pSpiP$S_POWER_ON, 5);
  return SUCCESS;
}

# 55 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpi.nc"
inline static   error_t Stm25pSectorP$Spi$powerUp(void){
#line 55
  unsigned char result;
#line 55

#line 55
  result = Stm25pSpiP$Spi$powerUp();
#line 55

#line 55
  return result;
#line 55
}
#line 55
# 130 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline  void Stm25pSectorP$SpiResource$granted(void)
#line 130
{
  error_t error;
  Stm25pSectorP$stm25p_power_state_t power_state = Stm25pSectorP$m_power_state;

#line 133
  Stm25pSectorP$m_power_state = Stm25pSectorP$S_NONE;
  if (power_state == Stm25pSectorP$S_START) {
      error = Stm25pSectorP$Spi$powerUp();
      Stm25pSectorP$SpiResource$release();
      Stm25pSectorP$SplitControl$startDone(error);
      return;
    }
  else {
#line 140
    if (power_state == Stm25pSectorP$S_STOP) {
        error = Stm25pSectorP$Spi$powerDown();
        Stm25pSectorP$SpiResource$release();
        Stm25pSectorP$SplitControl$stopDone(error);
        return;
      }
    }
#line 146
  Stm25pSectorP$ClientResource$granted(Stm25pSectorP$m_client);
}

# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static  void Stm25pSpiP$ClientResource$granted(void){
#line 92
  Stm25pSectorP$SpiResource$granted();
#line 92
}
#line 92
# 238 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpiP.nc"
static inline  void Stm25pSpiP$SpiResource$granted(void)
#line 238
{

  if (!Stm25pSpiP$m_is_writing) {
    Stm25pSpiP$ClientResource$granted();
    }
  else {
#line 242
    if (Stm25pSpiP$sendCmd(0x5, 2) & 0x1) {
      Stm25pSpiP$releaseAndRequest();
      }
    else {
#line 245
      Stm25pSpiP$signalDone(SUCCESS);
      }
    }
}

# 118 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
static inline   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Resource$default$granted(uint8_t id)
#line 118
{
}

# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static  void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Resource$granted(uint8_t arg_0x40a7e488){
#line 92
  switch (arg_0x40a7e488) {
#line 92
    case /*HplStm25pSpiC.SpiC*/Msp430Spi0C$0$CLIENT_ID:
#line 92
      Stm25pSpiP$SpiResource$granted();
#line 92
      break;
#line 92
    default:
#line 92
      /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Resource$default$granted(arg_0x40a7e488);
#line 92
      break;
#line 92
    }
#line 92
}
#line 92
# 94 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
static inline  void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$granted(uint8_t id)
#line 94
{
  /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Resource$granted(id);
}

# 194 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Resource$default$granted(uint8_t id)
#line 194
{
}

# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static  void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Resource$granted(uint8_t arg_0x4089ff00){
#line 92
  switch (arg_0x4089ff00) {
#line 92
    case /*HplStm25pSpiC.SpiC.UsartC*/Msp430Usart0C$0$CLIENT_ID:
#line 92
      /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$granted(/*HplStm25pSpiC.SpiC*/Msp430Spi0C$0$CLIENT_ID);
#line 92
      break;
#line 92
    default:
#line 92
      /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Resource$default$granted(arg_0x4089ff00);
#line 92
      break;
#line 92
    }
#line 92
}
#line 92
# 114 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
static inline    msp430_spi_union_config_t */*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Msp430SpiConfigure$default$getConfig(uint8_t id)
#line 114
{
  return &msp430_spi_default_config;
}

# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiConfigure.nc"
inline static   msp430_spi_union_config_t */*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Msp430SpiConfigure$getConfig(uint8_t arg_0x40a7c850){
#line 39
  union __nesc_unnamed4285 *result;
#line 39

#line 39
    result = /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Msp430SpiConfigure$default$getConfig(arg_0x40a7c850);
#line 39

#line 39
  return result;
#line 39
}
#line 39
# 357 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart0P.nc"
static inline   void HplMsp430Usart0P$Usart$disableIntr(void)
#line 357
{
  HplMsp430Usart0P$IE1 &= ~((1 << 7) | (1 << 6));
}

#line 345
static inline   void HplMsp430Usart0P$Usart$clrIntr(void)
#line 345
{
  HplMsp430Usart0P$IFG1 &= ~((1 << 7) | (1 << 6));
}

#line 151
static inline   void HplMsp430Usart0P$Usart$resetUsart(bool reset)
#line 151
{
  if (reset) {
      U0CTL = 0x01;
    }
  else {
      U0CTL &= ~0x01;
    }
}

# 54 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P33*/HplMsp430GeneralIOP$19$IO$selectModuleFunc(void)
#line 54
{
  /* atomic removed: atomic calls only */
#line 54
  * (volatile uint8_t *)27U |= 0x01 << 3;
}

# 78 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void HplMsp430Usart0P$UCLK$selectModuleFunc(void){
#line 78
  /*HplMsp430GeneralIOC.P33*/HplMsp430GeneralIOP$19$IO$selectModuleFunc();
#line 78
}
#line 78
# 54 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P32*/HplMsp430GeneralIOP$18$IO$selectModuleFunc(void)
#line 54
{
  /* atomic removed: atomic calls only */
#line 54
  * (volatile uint8_t *)27U |= 0x01 << 2;
}

# 78 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void HplMsp430Usart0P$SOMI$selectModuleFunc(void){
#line 78
  /*HplMsp430GeneralIOC.P32*/HplMsp430GeneralIOP$18$IO$selectModuleFunc();
#line 78
}
#line 78
# 54 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P31*/HplMsp430GeneralIOP$17$IO$selectModuleFunc(void)
#line 54
{
  /* atomic removed: atomic calls only */
#line 54
  * (volatile uint8_t *)27U |= 0x01 << 1;
}

# 78 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void HplMsp430Usart0P$SIMO$selectModuleFunc(void){
#line 78
  /*HplMsp430GeneralIOC.P31*/HplMsp430GeneralIOP$17$IO$selectModuleFunc();
#line 78
}
#line 78
# 238 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart0P.nc"
static inline   void HplMsp430Usart0P$Usart$enableSpi(void)
#line 238
{
  /* atomic removed: atomic calls only */
#line 239
  {
    HplMsp430Usart0P$SIMO$selectModuleFunc();
    HplMsp430Usart0P$SOMI$selectModuleFunc();
    HplMsp430Usart0P$UCLK$selectModuleFunc();
  }
  HplMsp430Usart0P$ME1 |= 1 << 6;
}

#line 143
static inline   void HplMsp430Usart0P$Usart$setUmctl(uint8_t control)
#line 143
{
  U0MCTL = control;
}

#line 132
static inline   void HplMsp430Usart0P$Usart$setUbr(uint16_t control)
#line 132
{
  /* atomic removed: atomic calls only */
#line 133
  {
    U0BR0 = control & 0x00FF;
    U0BR1 = (control >> 8) & 0x00FF;
  }
}

#line 256
static inline void HplMsp430Usart0P$configSpi(msp430_spi_union_config_t *config)
#line 256
{

  U0CTL = (config->spiRegisters.uctl | 0x04) | 0x01;
  HplMsp430Usart0P$U0TCTL = config->spiRegisters.utctl;

  HplMsp430Usart0P$Usart$setUbr(config->spiRegisters.ubr);
  HplMsp430Usart0P$Usart$setUmctl(0x00);
}

# 56 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P35*/HplMsp430GeneralIOP$21$IO$selectIOFunc(void)
#line 56
{
  /* atomic removed: atomic calls only */
#line 56
  * (volatile uint8_t *)27U &= ~(0x01 << 5);
}

# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void HplMsp430Usart0P$URXD$selectIOFunc(void){
#line 85
  /*HplMsp430GeneralIOC.P35*/HplMsp430GeneralIOP$21$IO$selectIOFunc();
#line 85
}
#line 85
# 56 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P34*/HplMsp430GeneralIOP$20$IO$selectIOFunc(void)
#line 56
{
  /* atomic removed: atomic calls only */
#line 56
  * (volatile uint8_t *)27U &= ~(0x01 << 4);
}

# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void HplMsp430Usart0P$UTXD$selectIOFunc(void){
#line 85
  /*HplMsp430GeneralIOC.P34*/HplMsp430GeneralIOP$20$IO$selectIOFunc();
#line 85
}
#line 85
# 207 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart0P.nc"
static inline   void HplMsp430Usart0P$Usart$disableUart(void)
#line 207
{
  /* atomic removed: atomic calls only */
#line 208
  {
    HplMsp430Usart0P$ME1 &= ~((1 << 7) | (1 << 6));
    HplMsp430Usart0P$UTXD$selectIOFunc();
    HplMsp430Usart0P$URXD$selectIOFunc();
  }
}

# 97 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
inline static   void HplMsp430I2C0P$HplUsart$resetUsart(bool arg_0x40a98a30){
#line 97
  HplMsp430Usart0P$Usart$resetUsart(arg_0x40a98a30);
#line 97
}
#line 97
# 59 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430I2C0P.nc"
static inline   void HplMsp430I2C0P$HplI2C$clearModeI2C(void)
#line 59
{
  /* atomic removed: atomic calls only */
#line 60
  {
    HplMsp430I2C0P$U0CTL &= ~((0x20 | 0x04) | 0x01);
    HplMsp430I2C0P$HplUsart$resetUsart(TRUE);
  }
}

# 7 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430I2C.nc"
inline static   void HplMsp430Usart0P$HplI2C$clearModeI2C(void){
#line 7
  HplMsp430I2C0P$HplI2C$clearModeI2C();
#line 7
}
#line 7
# 265 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart0P.nc"
static inline   void HplMsp430Usart0P$Usart$setModeSpi(msp430_spi_union_config_t *config)
#line 265
{

  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 267
    {
      HplMsp430Usart0P$Usart$resetUsart(TRUE);
      HplMsp430Usart0P$HplI2C$clearModeI2C();
      HplMsp430Usart0P$Usart$disableUart();
      HplMsp430Usart0P$configSpi(config);
      HplMsp430Usart0P$Usart$enableSpi();
      HplMsp430Usart0P$Usart$resetUsart(FALSE);
      HplMsp430Usart0P$Usart$clrIntr();
      HplMsp430Usart0P$Usart$disableIntr();
    }
#line 276
    __nesc_atomic_end(__nesc_atomic); }
  return;
}

# 168 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
inline static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$setModeSpi(msp430_spi_union_config_t *arg_0x40a94aa0){
#line 168
  HplMsp430Usart0P$Usart$setModeSpi(arg_0x40a94aa0);
#line 168
}
#line 168
# 84 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
static inline   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$ResourceConfigure$configure(uint8_t id)
#line 84
{
  /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$setModeSpi(/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Msp430SpiConfigure$getConfig(id));
}

# 208 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline    void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceConfigure$default$configure(uint8_t id)
#line 208
{
}

# 49 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
inline static   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceConfigure$configure(uint8_t arg_0x4089c9b8){
#line 49
  switch (arg_0x4089c9b8) {
#line 49
    case /*HplStm25pSpiC.SpiC.UsartC*/Msp430Usart0C$0$CLIENT_ID:
#line 49
      /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$ResourceConfigure$configure(/*HplStm25pSpiC.SpiC*/Msp430Spi0C$0$CLIENT_ID);
#line 49
      break;
#line 49
    default:
#line 49
      /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceConfigure$default$configure(arg_0x4089c9b8);
#line 49
      break;
#line 49
    }
#line 49
}
#line 49
# 182 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline  void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$grantedTask$runTask(void)
#line 182
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 183
    {
      /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$resId = /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$reqResId;
      /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$state = /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$RES_BUSY;
    }
#line 186
    __nesc_atomic_end(__nesc_atomic); }
  /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceConfigure$configure(/*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$resId);
  /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Resource$granted(/*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$resId);
}

# 39 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$HplGeneralIO$clr(void){
#line 39
  /*HplMsp430GeneralIOC.P44*/HplMsp430GeneralIOP$28$IO$clr();
#line 39
}
#line 39
# 38 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$GeneralIO$clr(void)
#line 38
{
#line 38
  /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$HplGeneralIO$clr();
}

# 30 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
inline static   void Stm25pSpiP$CSN$clr(void){
#line 30
  /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$GeneralIO$clr();
#line 30
}
#line 30
# 231 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
inline static   uint8_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$rx(void){
#line 231
  unsigned char result;
#line 231

#line 231
  result = HplMsp430Usart0P$Usart$rx();
#line 231

#line 231
  return result;
#line 231
}
#line 231
# 341 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart0P.nc"
static inline   void HplMsp430Usart0P$Usart$clrRxIntr(void)
#line 341
{
  HplMsp430Usart0P$IFG1 &= ~(1 << 6);
}

# 197 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
inline static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$clrRxIntr(void){
#line 197
  HplMsp430Usart0P$Usart$clrRxIntr();
#line 197
}
#line 197
# 330 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart0P.nc"
static inline   bool HplMsp430Usart0P$Usart$isRxIntrPending(void)
#line 330
{
  if (HplMsp430Usart0P$IFG1 & (1 << 6)) {
      return TRUE;
    }
  return FALSE;
}

# 192 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
inline static   bool /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$isRxIntrPending(void){
#line 192
  unsigned char result;
#line 192

#line 192
  result = HplMsp430Usart0P$Usart$isRxIntrPending();
#line 192

#line 192
  return result;
#line 192
}
#line 192
# 382 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart0P.nc"
static inline   void HplMsp430Usart0P$Usart$tx(uint8_t data)
#line 382
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 383
    HplMsp430Usart0P$U0TXBUF = data;
#line 383
    __nesc_atomic_end(__nesc_atomic); }
}

# 224 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
inline static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$tx(uint8_t arg_0x40a8fc18){
#line 224
  HplMsp430Usart0P$Usart$tx(arg_0x40a8fc18);
#line 224
}
#line 224
# 98 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
static inline   uint8_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$SpiByte$write(uint8_t tx)
#line 98
{
  uint8_t byte;


  /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$tx(tx);
  while (!/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$isRxIntrPending()) ;
  /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$clrRxIntr();
  byte = /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$rx();

  return byte;
}

# 34 "/opt/tinyos-2.x/tos/interfaces/SpiByte.nc"
inline static   uint8_t Stm25pSpiP$SpiByte$write(uint8_t arg_0x40a12a18){
#line 34
  unsigned char result;
#line 34

#line 34
  result = /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$SpiByte$write(arg_0x40a12a18);
#line 34

#line 34
  return result;
#line 34
}
#line 34
# 50 "/opt/tinyos-2.x/tos/system/FcfsResourceQueueC.nc"
static inline   bool /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$FcfsQueue$isEmpty(void)
#line 50
{
  return /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$qHead == /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$NO_ENTRY;
}

# 43 "/opt/tinyos-2.x/tos/interfaces/ResourceQueue.nc"
inline static   bool /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Queue$isEmpty(void){
#line 43
  unsigned char result;
#line 43

#line 43
  result = /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$FcfsQueue$isEmpty();
#line 43

#line 43
  return result;
#line 43
}
#line 43
# 58 "/opt/tinyos-2.x/tos/system/FcfsResourceQueueC.nc"
static inline   resource_client_id_t /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$FcfsQueue$dequeue(void)
#line 58
{
  /* atomic removed: atomic calls only */
#line 59
  {
    if (/*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$qHead != /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$NO_ENTRY) {
        uint8_t id = /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$qHead;

#line 62
        /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$qHead = /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$resQ[/*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$qHead];
        if (/*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$qHead == /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$NO_ENTRY) {
          /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$qTail = /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$NO_ENTRY;
          }
#line 65
        /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$resQ[id] = /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$NO_ENTRY;
        {
          unsigned char __nesc_temp = 
#line 66
          id;

#line 66
          return __nesc_temp;
        }
      }
#line 68
    {
      unsigned char __nesc_temp = 
#line 68
      /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$NO_ENTRY;

#line 68
      return __nesc_temp;
    }
  }
}

# 60 "/opt/tinyos-2.x/tos/interfaces/ResourceQueue.nc"
inline static   resource_client_id_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Queue$dequeue(void){
#line 60
  unsigned char result;
#line 60

#line 60
  result = /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$FcfsQueue$dequeue();
#line 60

#line 60
  return result;
#line 60
}
#line 60
# 200 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline    void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$default$granted(void)
#line 200
{
}

# 46 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
inline static   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$granted(void){
#line 46
  /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$default$granted();
#line 46
}
#line 46
# 97 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
inline static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$resetUsart(bool arg_0x40a98a30){
#line 97
  HplMsp430Usart0P$Usart$resetUsart(arg_0x40a98a30);
#line 97
}
#line 97
# 56 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P33*/HplMsp430GeneralIOP$19$IO$selectIOFunc(void)
#line 56
{
  /* atomic removed: atomic calls only */
#line 56
  * (volatile uint8_t *)27U &= ~(0x01 << 3);
}

# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void HplMsp430Usart0P$UCLK$selectIOFunc(void){
#line 85
  /*HplMsp430GeneralIOC.P33*/HplMsp430GeneralIOP$19$IO$selectIOFunc();
#line 85
}
#line 85
# 56 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P32*/HplMsp430GeneralIOP$18$IO$selectIOFunc(void)
#line 56
{
  /* atomic removed: atomic calls only */
#line 56
  * (volatile uint8_t *)27U &= ~(0x01 << 2);
}

# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void HplMsp430Usart0P$SOMI$selectIOFunc(void){
#line 85
  /*HplMsp430GeneralIOC.P32*/HplMsp430GeneralIOP$18$IO$selectIOFunc();
#line 85
}
#line 85
# 56 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P31*/HplMsp430GeneralIOP$17$IO$selectIOFunc(void)
#line 56
{
  /* atomic removed: atomic calls only */
#line 56
  * (volatile uint8_t *)27U &= ~(0x01 << 1);
}

# 85 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void HplMsp430Usart0P$SIMO$selectIOFunc(void){
#line 85
  /*HplMsp430GeneralIOC.P31*/HplMsp430GeneralIOP$17$IO$selectIOFunc();
#line 85
}
#line 85
# 247 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart0P.nc"
static inline   void HplMsp430Usart0P$Usart$disableSpi(void)
#line 247
{
  /* atomic removed: atomic calls only */
#line 248
  {
    HplMsp430Usart0P$ME1 &= ~(1 << 6);
    HplMsp430Usart0P$SIMO$selectIOFunc();
    HplMsp430Usart0P$SOMI$selectIOFunc();
    HplMsp430Usart0P$UCLK$selectIOFunc();
  }
}

# 158 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
inline static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$disableSpi(void){
#line 158
  HplMsp430Usart0P$Usart$disableSpi();
#line 158
}
#line 158
# 88 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
static inline   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$ResourceConfigure$unconfigure(uint8_t id)
#line 88
{
  /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$resetUsart(TRUE);
  /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$disableSpi();
  /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$resetUsart(FALSE);
}

# 210 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline    void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceConfigure$default$unconfigure(uint8_t id)
#line 210
{
}

# 55 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
inline static   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceConfigure$unconfigure(uint8_t arg_0x4089c9b8){
#line 55
  switch (arg_0x4089c9b8) {
#line 55
    case /*HplStm25pSpiC.SpiC.UsartC*/Msp430Usart0C$0$CLIENT_ID:
#line 55
      /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$ResourceConfigure$unconfigure(/*HplStm25pSpiC.SpiC*/Msp430Spi0C$0$CLIENT_ID);
#line 55
      break;
#line 55
    default:
#line 55
      /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceConfigure$default$unconfigure(arg_0x4089c9b8);
#line 55
      break;
#line 55
    }
#line 55
}
#line 55
# 196 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline    void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceRequested$default$requested(uint8_t id)
#line 196
{
}

# 43 "/opt/tinyos-2.x/tos/interfaces/ResourceRequested.nc"
inline static   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceRequested$requested(uint8_t arg_0x4089e830){
#line 43
    /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceRequested$default$requested(arg_0x4089e830);
#line 43
}
#line 43
# 54 "/opt/tinyos-2.x/tos/system/FcfsResourceQueueC.nc"
static inline   bool /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$FcfsQueue$isEnqueued(resource_client_id_t id)
#line 54
{
  return /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$resQ[id] != /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$NO_ENTRY || /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$qTail == id;
}

#line 72
static inline   error_t /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$FcfsQueue$enqueue(resource_client_id_t id)
#line 72
{
  /* atomic removed: atomic calls only */
#line 73
  {
    if (!/*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$FcfsQueue$isEnqueued(id)) {
        if (/*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$qHead == /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$NO_ENTRY) {
          /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$qHead = id;
          }
        else {
#line 78
          /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$resQ[/*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$qTail] = id;
          }
#line 79
        /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$qTail = id;
        {
          unsigned char __nesc_temp = 
#line 80
          SUCCESS;

#line 80
          return __nesc_temp;
        }
      }
#line 82
    {
      unsigned char __nesc_temp = 
#line 82
      EBUSY;

#line 82
      return __nesc_temp;
    }
  }
}

# 69 "/opt/tinyos-2.x/tos/interfaces/ResourceQueue.nc"
inline static   error_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Queue$enqueue(resource_client_id_t arg_0x4088c458){
#line 69
  unsigned char result;
#line 69

#line 69
  result = /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$FcfsQueue$enqueue(arg_0x4088c458);
#line 69

#line 69
  return result;
#line 69
}
#line 69
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
inline static   error_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$grantedTask$postTask(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = SchedulerBasicP$TaskBasic$postTask(/*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$grantedTask);
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 127 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline   error_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$release(void)
#line 127
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 128
    {
      if (/*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$resId == /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$default_owner_id) {
          if (/*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$state == /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$RES_GRANTING) {
              /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$grantedTask$postTask();
              {
                unsigned char __nesc_temp = 
#line 132
                SUCCESS;

                {
#line 132
                  __nesc_atomic_end(__nesc_atomic); 
#line 132
                  return __nesc_temp;
                }
              }
            }
          else {
#line 134
            if (/*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$state == /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$RES_IMM_GRANTING) {
                /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$resId = /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$reqResId;
                /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$state = /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$RES_BUSY;
                {
                  unsigned char __nesc_temp = 
#line 137
                  SUCCESS;

                  {
#line 137
                    __nesc_atomic_end(__nesc_atomic); 
#line 137
                    return __nesc_temp;
                  }
                }
              }
            }
        }
    }
#line 143
    __nesc_atomic_end(__nesc_atomic); }
#line 141
  return FAIL;
}

#line 202
static inline    void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$default$requested(void)
#line 202
{
  /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$release();
}

# 73 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
inline static   void /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$requested(void){
#line 73
  /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$default$requested();
#line 73
}
#line 73
# 138 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpiP.nc"
static inline   error_t Stm25pSpiP$Spi$read(stm25p_addr_t addr, uint8_t *buf, 
stm25p_len_t len)
#line 139
{
  Stm25pSpiP$m_cmd[0] = Stm25pSpiP$S_READ;
  Stm25pSpiP$m_addr = addr;
  Stm25pSpiP$m_buf = buf;
  Stm25pSpiP$m_len = len;
  return Stm25pSpiP$newRequest(FALSE, 4);
}

# 66 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpi.nc"
inline static   error_t Stm25pSectorP$Spi$read(stm25p_addr_t arg_0x40821350, uint8_t *arg_0x408214f0, stm25p_len_t arg_0x40821688){
#line 66
  unsigned char result;
#line 66

#line 66
  result = Stm25pSpiP$Spi$read(arg_0x40821350, arg_0x408214f0, arg_0x40821688);
#line 66

#line 66
  return result;
#line 66
}
#line 66
# 59 "/opt/tinyos-2.x/tos/interfaces/SpiPacket.nc"
inline static   error_t Stm25pSpiP$SpiPacket$send(uint8_t *arg_0x40a0e320, uint8_t *arg_0x40a0e4c0, uint16_t arg_0x40a0e648){
#line 59
  unsigned char result;
#line 59

#line 59
  result = /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$SpiPacket$send(/*HplStm25pSpiC.SpiC*/Msp430Spi0C$0$CLIENT_ID, arg_0x40a0e320, arg_0x40a0e4c0, arg_0x40a0e648);
#line 59

#line 59
  return result;
#line 59
}
#line 59
# 361 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart0P.nc"
static inline   void HplMsp430Usart0P$Usart$enableRxIntr(void)
#line 361
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 362
    {
      HplMsp430Usart0P$IFG1 &= ~(1 << 6);
      HplMsp430Usart0P$IE1 |= 1 << 6;
    }
#line 365
    __nesc_atomic_end(__nesc_atomic); }
}

# 180 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
inline static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$enableRxIntr(void){
#line 180
  HplMsp430Usart0P$Usart$enableRxIntr();
#line 180
}
#line 180
# 316 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart0P.nc"
static inline   bool HplMsp430Usart0P$Usart$isTxIntrPending(void)
#line 316
{
  if (HplMsp430Usart0P$IFG1 & (1 << 7)) {
      return TRUE;
    }
  return FALSE;
}

# 187 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
inline static   bool /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$isTxIntrPending(void){
#line 187
  unsigned char result;
#line 187

#line 187
  result = HplMsp430Usart0P$Usart$isTxIntrPending();
#line 187

#line 187
  return result;
#line 187
}
#line 187
# 337 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart0P.nc"
static inline   void HplMsp430Usart0P$Usart$clrTxIntr(void)
#line 337
{
  HplMsp430Usart0P$IFG1 &= ~(1 << 7);
}

# 202 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
inline static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$clrTxIntr(void){
#line 202
  HplMsp430Usart0P$Usart$clrTxIntr();
#line 202
}
#line 202
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
inline static   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$signalDone_task$postTask(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = SchedulerBasicP$TaskBasic$postTask(/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$signalDone_task);
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 45 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pBinderP.nc"
static inline   volume_id_t /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC.BinderP*/Stm25pBinderP$0$Volume$getVolumeId(void)
#line 45
{
  return 0;
}

# 289 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline    volume_id_t Stm25pSectorP$Volume$default$getVolumeId(uint8_t id)
#line 289
{
#line 289
  return 0xff;
}

# 48 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pVolume.nc"
inline static   volume_id_t Stm25pSectorP$Volume$getVolumeId(uint8_t arg_0x40828778){
#line 48
  unsigned char result;
#line 48

#line 48
  switch (arg_0x40828778) {
#line 48
    case /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$VOLUME_ID:
#line 48
      result = /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC.BinderP*/Stm25pBinderP$0$Volume$getVolumeId();
#line 48
      break;
#line 48
    default:
#line 48
      result = Stm25pSectorP$Volume$default$getVolumeId(arg_0x40828778);
#line 48
      break;
#line 48
    }
#line 48

#line 48
  return result;
#line 48
}
#line 48
# 126 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline uint8_t Stm25pSectorP$getVolumeId(uint8_t client)
#line 126
{
  return Stm25pSectorP$Volume$getVolumeId(client);
}

#line 153
static inline stm25p_addr_t Stm25pSectorP$physicalAddr(uint8_t id, stm25p_addr_t addr)
#line 153
{
  return addr + ((stm25p_addr_t )STM25P_VMAP[Stm25pSectorP$getVolumeId(id)].base
   << STM25P_SECTOR_SIZE_LOG2);
}

# 210 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline    void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceConfigure$default$unconfigure(uint8_t id)
#line 210
{
}

# 55 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
inline static   void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceConfigure$unconfigure(uint8_t arg_0x4089c9b8){
#line 55
    /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceConfigure$default$unconfigure(arg_0x4089c9b8);
#line 55
}
#line 55
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
inline static   error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$timerTask$postTask(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = SchedulerBasicP$TaskBasic$postTask(/*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$timerTask);
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 103 "/opt/tinyos-2.x/tos/lib/power/DeferredPowerManagerP.nc"
static inline   void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$ResourceDefaultOwner$granted(void)
#line 103
{
  /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$timerTask$postTask();
}

# 46 "/opt/tinyos-2.x/tos/interfaces/ResourceDefaultOwner.nc"
inline static   void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceDefaultOwner$granted(void){
#line 46
  /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$ResourceDefaultOwner$granted();
#line 46
}
#line 46
# 58 "/opt/tinyos-2.x/tos/system/FcfsResourceQueueC.nc"
static inline   resource_client_id_t /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$FcfsQueue$dequeue(void)
#line 58
{
  /* atomic removed: atomic calls only */
#line 59
  {
    if (/*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$qHead != /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$NO_ENTRY) {
        uint8_t id = /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$qHead;

#line 62
        /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$qHead = /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$resQ[/*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$qHead];
        if (/*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$qHead == /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$NO_ENTRY) {
          /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$qTail = /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$NO_ENTRY;
          }
#line 65
        /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$resQ[id] = /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$NO_ENTRY;
        {
          unsigned char __nesc_temp = 
#line 66
          id;

#line 66
          return __nesc_temp;
        }
      }
#line 68
    {
      unsigned char __nesc_temp = 
#line 68
      /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$NO_ENTRY;

#line 68
      return __nesc_temp;
    }
  }
}

# 60 "/opt/tinyos-2.x/tos/interfaces/ResourceQueue.nc"
inline static   resource_client_id_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Queue$dequeue(void){
#line 60
  unsigned char result;
#line 60

#line 60
  result = /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$FcfsQueue$dequeue();
#line 60

#line 60
  return result;
#line 60
}
#line 60
# 50 "/opt/tinyos-2.x/tos/system/FcfsResourceQueueC.nc"
static inline   bool /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$FcfsQueue$isEmpty(void)
#line 50
{
  return /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$qHead == /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$NO_ENTRY;
}

# 43 "/opt/tinyos-2.x/tos/interfaces/ResourceQueue.nc"
inline static   bool /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Queue$isEmpty(void){
#line 43
  unsigned char result;
#line 43

#line 43
  result = /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$FcfsQueue$isEmpty();
#line 43

#line 43
  return result;
#line 43
}
#line 43
# 108 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline   error_t /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Resource$release(uint8_t id)
#line 108
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 109
    {
      if (/*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$state == /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$RES_BUSY && /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$resId == id) {
          if (/*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Queue$isEmpty() == FALSE) {
              /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$reqResId = /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Queue$dequeue();
              /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$state = /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$RES_GRANTING;
              /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$grantedTask$postTask();
            }
          else {
              /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$resId = /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$default_owner_id;
              /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$state = /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$RES_CONTROLLED;
              /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceDefaultOwner$granted();
            }
          /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceConfigure$unconfigure(id);
        }
    }
#line 123
    __nesc_atomic_end(__nesc_atomic); }
  return FAIL;
}

# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static   error_t Stm25pSectorP$Stm25pResource$release(uint8_t arg_0x40828d68){
#line 110
  unsigned char result;
#line 110

#line 110
  result = /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Resource$release(arg_0x40828d68);
#line 110

#line 110
  return result;
#line 110
}
#line 110
# 110 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline   error_t Stm25pSectorP$ClientResource$release(uint8_t id)
#line 110
{
  if (Stm25pSectorP$m_client == id) {
      Stm25pSectorP$m_state = Stm25pSectorP$S_IDLE;
      Stm25pSectorP$m_client = Stm25pSectorP$NO_CLIENT;
      Stm25pSectorP$SpiResource$release();
      Stm25pSectorP$Stm25pResource$release(id);
      return SUCCESS;
    }
  return FAIL;
}

# 527 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline    error_t Stm25pLogP$ClientResource$default$release(uint8_t id)
#line 527
{
#line 527
  return FAIL;
}

# 110 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static   error_t Stm25pLogP$ClientResource$release(uint8_t arg_0x407a8360){
#line 110
  unsigned char result;
#line 110

#line 110
  switch (arg_0x407a8360) {
#line 110
    case /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$LOG_ID:
#line 110
      result = Stm25pSectorP$ClientResource$release(/*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$VOLUME_ID);
#line 110
      break;
#line 110
    default:
#line 110
      result = Stm25pLogP$ClientResource$default$release(arg_0x407a8360);
#line 110
      break;
#line 110
    }
#line 110

#line 110
  return result;
#line 110
}
#line 110
# 64 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
inline static  error_t /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$Send$send(message_t *arg_0x40bf8a90, uint8_t arg_0x40bf8c10){
#line 64
  unsigned char result;
#line 64

#line 64
  result = /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Send$send(1U, arg_0x40bf8a90, arg_0x40bf8c10);
#line 64

#line 64
  return result;
#line 64
}
#line 64
# 151 "/opt/tinyos-2.x/tos/interfaces/AMPacket.nc"
inline static  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$AMPacket$setType(message_t *arg_0x40be2d40, am_id_t arg_0x40be2ec0){
#line 151
  /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$setType(arg_0x40be2d40, arg_0x40be2ec0);
#line 151
}
#line 151
# 174 "/opt/nesc-1.2.7a/lib/ncc/nesc_nx.h"
static __inline uint16_t __nesc_hton_uint16(void *target, uint16_t value)
#line 174
{
  uint8_t *base = target;

#line 176
  base[1] = value;
  base[0] = value >> 8;
  return value;
}

# 137 "/opt/tinyos-2.x/tos/lib/serial/SerialActiveMessageP.nc"
static inline  void /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$setDestination(message_t *amsg, am_addr_t addr)
#line 137
{
  serial_header_t *header = /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$getHeader(amsg);

#line 139
  __nesc_hton_uint16((unsigned char *)&header->dest, addr);
}

# 92 "/opt/tinyos-2.x/tos/interfaces/AMPacket.nc"
inline static  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$AMPacket$setDestination(message_t *arg_0x40be5ee8, am_addr_t arg_0x40be3088){
#line 92
  /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$setDestination(arg_0x40be5ee8, arg_0x40be3088);
#line 92
}
#line 92
# 45 "/opt/tinyos-2.x/tos/system/AMQueueEntryP.nc"
static inline  error_t /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$AMSend$send(am_addr_t dest, 
message_t *msg, 
uint8_t len)
#line 47
{
  /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$AMPacket$setDestination(msg, dest);
  /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$AMPacket$setType(msg, 29);
  return /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$Send$send(msg, len);
}

# 69 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
inline static  error_t TraceRetrieverP$UARTSendAck$send(am_addr_t arg_0x40637b40, message_t *arg_0x40637ce8, uint8_t arg_0x40637e68){
#line 69
  unsigned char result;
#line 69

#line 69
  result = /*TraceRetrieverAppC.TraceRetrieverC.UARTSendAck.AMQueueEntryP*/AMQueueEntryP$1$AMSend$send(arg_0x40637b40, arg_0x40637ce8, arg_0x40637e68);
#line 69

#line 69
  return result;
#line 69
}
#line 69
# 64 "/opt/tinyos-2.x/tos/interfaces/Send.nc"
inline static  error_t /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$Send$send(message_t *arg_0x40bf8a90, uint8_t arg_0x40bf8c10){
#line 64
  unsigned char result;
#line 64

#line 64
  result = /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Send$send(0U, arg_0x40bf8a90, arg_0x40bf8c10);
#line 64

#line 64
  return result;
#line 64
}
#line 64
# 151 "/opt/tinyos-2.x/tos/interfaces/AMPacket.nc"
inline static  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$AMPacket$setType(message_t *arg_0x40be2d40, am_id_t arg_0x40be2ec0){
#line 151
  /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$setType(arg_0x40be2d40, arg_0x40be2ec0);
#line 151
}
#line 151
#line 92
inline static  void /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$AMPacket$setDestination(message_t *arg_0x40be5ee8, am_addr_t arg_0x40be3088){
#line 92
  /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$setDestination(arg_0x40be5ee8, arg_0x40be3088);
#line 92
}
#line 92
# 45 "/opt/tinyos-2.x/tos/system/AMQueueEntryP.nc"
static inline  error_t /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$AMSend$send(am_addr_t dest, 
message_t *msg, 
uint8_t len)
#line 47
{
  /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$AMPacket$setDestination(msg, dest);
  /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$AMPacket$setType(msg, 28);
  return /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$Send$send(msg, len);
}

# 69 "/opt/tinyos-2.x/tos/interfaces/AMSend.nc"
inline static  error_t TraceRetrieverP$UARTSendData$send(am_addr_t arg_0x40637b40, message_t *arg_0x40637ce8, uint8_t arg_0x40637e68){
#line 69
  unsigned char result;
#line 69

#line 69
  result = /*TraceRetrieverAppC.TraceRetrieverC.UARTSendData.AMQueueEntryP*/AMQueueEntryP$0$AMSend$send(arg_0x40637b40, arg_0x40637ce8, arg_0x40637e68);
#line 69

#line 69
  return result;
#line 69
}
#line 69
# 106 "TraceRetrieverP.nc"
static __inline void TraceRetrieverP$sendLogData(void)
#line 106
{
  memcpy(TraceRetrieverP$logEntry, &TraceRetrieverP$traceLogEntry.entries[TraceRetrieverP$logIndex], sizeof(TraceEntry ));
  TraceRetrieverP$UARTSendData$send(AM_BROADCAST_ADDR, &TraceRetrieverP$logData, sizeof(TraceEntry ));
}

#line 92
static inline  void TraceRetrieverP$LogRead$readDone(void *buf, storage_len_t length, error_t err)
#line 92
{
  if (err == SUCCESS) {
      if (length == sizeof(TraceLogEntry )) {
          TraceRetrieverP$logIndex = 0;
          TraceRetrieverP$sendLogData();
        }
      else 
#line 97
        {
          TraceRetrieverP$UARTSendAck$send(AM_BROADCAST_ADDR, &TraceRetrieverP$ack, sizeof(TraceMessage ));
        }
    }
  else 
#line 100
    {
      TraceRetrieverP$Leds$led0On();
    }
}

# 514 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline   void Stm25pLogP$Read$default$readDone(uint8_t id, void *data, storage_len_t len, error_t error)
#line 514
{
}

# 75 "/opt/tinyos-2.x/tos/interfaces/LogRead.nc"
inline static  void Stm25pLogP$Read$readDone(uint8_t arg_0x407a3868, void *arg_0x40613668, storage_len_t arg_0x406137f0, error_t arg_0x40613970){
#line 75
  switch (arg_0x407a3868) {
#line 75
    case /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$LOG_ID:
#line 75
      TraceRetrieverP$LogRead$readDone(arg_0x40613668, arg_0x406137f0, arg_0x40613970);
#line 75
      break;
#line 75
    default:
#line 75
      Stm25pLogP$Read$default$readDone(arg_0x407a3868, arg_0x40613668, arg_0x406137f0, arg_0x40613970);
#line 75
      break;
#line 75
    }
#line 75
}
#line 75
# 129 "TraceRetrieverP.nc"
static inline  void TraceRetrieverP$LogRead$seekDone(error_t err)
#line 129
{
}

# 515 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline   void Stm25pLogP$Read$default$seekDone(uint8_t id, error_t error)
#line 515
{
}

# 115 "/opt/tinyos-2.x/tos/interfaces/LogRead.nc"
inline static  void Stm25pLogP$Read$seekDone(uint8_t arg_0x407a3868, error_t arg_0x40612d00){
#line 115
  switch (arg_0x407a3868) {
#line 115
    case /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$LOG_ID:
#line 115
      TraceRetrieverP$LogRead$seekDone(arg_0x40612d00);
#line 115
      break;
#line 115
    default:
#line 115
      Stm25pLogP$Read$default$seekDone(arg_0x407a3868, arg_0x40612d00);
#line 115
      break;
#line 115
    }
#line 115
}
#line 115
# 516 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline   void Stm25pLogP$Write$default$eraseDone(uint8_t id, error_t error)
#line 516
{
}

# 100 "/opt/tinyos-2.x/tos/interfaces/LogWrite.nc"
inline static  void Stm25pLogP$Write$eraseDone(uint8_t arg_0x407a2340, error_t arg_0x4078f480){
#line 100
    Stm25pLogP$Write$default$eraseDone(arg_0x407a2340, arg_0x4078f480);
#line 100
}
#line 100
# 517 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline   void Stm25pLogP$Write$default$appendDone(uint8_t id, void *data, storage_len_t len, bool recordsLost, error_t error)
#line 517
{
}

# 68 "/opt/tinyos-2.x/tos/interfaces/LogWrite.nc"
inline static  void Stm25pLogP$Write$appendDone(uint8_t arg_0x407a2340, void *arg_0x40790010, storage_len_t arg_0x40790198, bool arg_0x40790320, error_t arg_0x407904b0){
#line 68
    Stm25pLogP$Write$default$appendDone(arg_0x407a2340, arg_0x40790010, arg_0x40790198, arg_0x40790320, arg_0x407904b0);
#line 68
}
#line 68
# 518 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline   void Stm25pLogP$Write$default$syncDone(uint8_t id, error_t error)
#line 518
{
}

# 118 "/opt/tinyos-2.x/tos/interfaces/LogWrite.nc"
inline static  void Stm25pLogP$Write$syncDone(uint8_t arg_0x407a2340, error_t arg_0x4078fe48){
#line 118
    Stm25pLogP$Write$default$syncDone(arg_0x407a2340, arg_0x4078fe48);
#line 118
}
#line 118
# 521 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline   uint8_t Stm25pLogP$Sector$default$getNumSectors(uint8_t id)
#line 521
{
#line 521
  return 0;
}

# 56 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
inline static  uint8_t Stm25pLogP$Sector$getNumSectors(uint8_t arg_0x407a0010){
#line 56
  unsigned char result;
#line 56

#line 56
  switch (arg_0x407a0010) {
#line 56
    case /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$LOG_ID:
#line 56
      result = Stm25pSectorP$Sector$getNumSectors(/*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$VOLUME_ID);
#line 56
      break;
#line 56
    default:
#line 56
      result = Stm25pLogP$Sector$default$getNumSectors(arg_0x407a0010);
#line 56
      break;
#line 56
    }
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 202 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline uint8_t Stm25pLogP$calcSector(uint8_t client, stm25p_addr_t addr)
#line 202
{
  uint8_t sector = Stm25pLogP$Sector$getNumSectors(client);

#line 204
  return (uint8_t )(addr >> STM25P_SECTOR_SIZE_LOG2) % sector;
}

#line 524
static inline   error_t Stm25pLogP$Sector$default$erase(uint8_t id, uint8_t sector, uint8_t num_sectors)
#line 524
{
#line 524
  return FAIL;
}

# 112 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
inline static  error_t Stm25pLogP$Sector$erase(uint8_t arg_0x407a0010, uint8_t arg_0x407b1af8, uint8_t arg_0x407b1c80){
#line 112
  unsigned char result;
#line 112

#line 112
  switch (arg_0x407a0010) {
#line 112
    case /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$LOG_ID:
#line 112
      result = Stm25pSectorP$Sector$erase(/*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$VOLUME_ID, arg_0x407b1af8, arg_0x407b1c80);
#line 112
      break;
#line 112
    default:
#line 112
      result = Stm25pLogP$Sector$default$erase(arg_0x407a0010, arg_0x407b1af8, arg_0x407b1c80);
#line 112
      break;
#line 112
    }
#line 112

#line 112
  return result;
#line 112
}
#line 112
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
inline static   error_t Stm25pSectorP$signalDone_task$postTask(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = SchedulerBasicP$TaskBasic$postTask(Stm25pSectorP$signalDone_task);
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 256 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline void Stm25pSectorP$signalDone(error_t error)
#line 256
{
  Stm25pSectorP$m_error = error;
  Stm25pSectorP$signalDone_task$postTask();
}

#line 246
static inline   void Stm25pSectorP$Spi$computeCrcDone(uint16_t crc, stm25p_addr_t addr, 
stm25p_len_t len, error_t error)
#line 247
{
  Stm25pSectorP$m_crc = crc;
  Stm25pSectorP$signalDone(SUCCESS);
}

# 101 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpi.nc"
inline static   void Stm25pSpiP$Spi$computeCrcDone(uint16_t arg_0x4083a0b0, stm25p_addr_t arg_0x4083a238, stm25p_len_t arg_0x4083a3d0, error_t arg_0x4083a550){
#line 101
  Stm25pSectorP$Spi$computeCrcDone(arg_0x4083a0b0, arg_0x4083a238, arg_0x4083a3d0, arg_0x4083a550);
#line 101
}
#line 101
# 183 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline   void Stm25pSectorP$Spi$readDone(stm25p_addr_t addr, uint8_t *buf, 
stm25p_len_t len, error_t error)
#line 184
{
  Stm25pSectorP$signalDone(error);
}

# 77 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpi.nc"
inline static   void Stm25pSpiP$Spi$readDone(stm25p_addr_t arg_0x40821c90, uint8_t *arg_0x40821e30, stm25p_len_t arg_0x4081f010, error_t arg_0x4081f190){
#line 77
  Stm25pSectorP$Spi$readDone(arg_0x40821c90, arg_0x40821e30, arg_0x4081f010, arg_0x4081f190);
#line 77
}
#line 77
#line 114
inline static   error_t Stm25pSectorP$Spi$pageProgram(stm25p_addr_t arg_0x4083ab50, uint8_t *arg_0x4083acf0, stm25p_len_t arg_0x4083ae88){
#line 114
  unsigned char result;
#line 114

#line 114
  result = Stm25pSpiP$Spi$pageProgram(arg_0x4083ab50, arg_0x4083acf0, arg_0x4083ae88);
#line 114

#line 114
  return result;
#line 114
}
#line 114
# 202 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline   void Stm25pSectorP$Spi$pageProgramDone(stm25p_addr_t addr, uint8_t *buf, 
stm25p_len_t len, error_t error)
#line 203
{
  addr += len;
  buf += len;
  Stm25pSectorP$m_cur_len -= len;
  if (!Stm25pSectorP$m_cur_len) {
    Stm25pSectorP$signalDone(SUCCESS);
    }
  else {
#line 210
    Stm25pSectorP$Spi$pageProgram(addr, buf, Stm25pSectorP$calcWriteLen(addr));
    }
}

# 125 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpi.nc"
inline static   void Stm25pSpiP$Spi$pageProgramDone(stm25p_addr_t arg_0x408394d8, uint8_t *arg_0x40839678, stm25p_len_t arg_0x40839810, error_t arg_0x40839990){
#line 125
  Stm25pSectorP$Spi$pageProgramDone(arg_0x408394d8, arg_0x40839678, arg_0x40839810, arg_0x40839990);
#line 125
}
#line 125
#line 136
inline static   error_t Stm25pSectorP$Spi$sectorErase(uint8_t arg_0x40838010){
#line 136
  unsigned char result;
#line 136

#line 136
  result = Stm25pSpiP$Spi$sectorErase(arg_0x40838010);
#line 136

#line 136
  return result;
#line 136
}
#line 136
# 226 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline   void Stm25pSectorP$Spi$sectorEraseDone(uint8_t sector, error_t error)
#line 226
{
  if (++Stm25pSectorP$m_cur_len < Stm25pSectorP$m_len) {
    Stm25pSectorP$Spi$sectorErase(STM25P_VMAP[Stm25pSectorP$getVolumeId(Stm25pSectorP$m_client)].base + Stm25pSectorP$m_addr + 
    Stm25pSectorP$m_cur_len);
    }
  else {
#line 231
    Stm25pSectorP$signalDone(error);
    }
}

# 144 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpi.nc"
inline static   void Stm25pSpiP$Spi$sectorEraseDone(uint8_t arg_0x408385b8, error_t arg_0x40838738){
#line 144
  Stm25pSectorP$Spi$sectorEraseDone(arg_0x408385b8, arg_0x40838738);
#line 144
}
#line 144
# 252 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline   void Stm25pSectorP$Spi$bulkEraseDone(error_t error)
#line 252
{
}

# 159 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpi.nc"
inline static   void Stm25pSpiP$Spi$bulkEraseDone(error_t arg_0x408360b8){
#line 159
  Stm25pSectorP$Spi$bulkEraseDone(arg_0x408360b8);
#line 159
}
#line 159
# 192 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
static inline    void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$SpiPacket$default$sendDone(uint8_t id, uint8_t *tx_buf, uint8_t *rx_buf, uint16_t len, error_t error)
#line 192
{
}

# 71 "/opt/tinyos-2.x/tos/interfaces/SpiPacket.nc"
inline static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$SpiPacket$sendDone(uint8_t arg_0x40a7d790, uint8_t *arg_0x40a0ed80, uint8_t *arg_0x40a0ef20, uint16_t arg_0x40a0d0c0, error_t arg_0x40a0d250){
#line 71
  switch (arg_0x40a7d790) {
#line 71
    case /*HplStm25pSpiC.SpiC*/Msp430Spi0C$0$CLIENT_ID:
#line 71
      Stm25pSpiP$SpiPacket$sendDone(arg_0x40a0ed80, arg_0x40a0ef20, arg_0x40a0d0c0, arg_0x40a0d250);
#line 71
      break;
#line 71
    default:
#line 71
      /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$SpiPacket$default$sendDone(arg_0x40a7d790, arg_0x40a0ed80, arg_0x40a0ef20, arg_0x40a0d0c0, arg_0x40a0d250);
#line 71
      break;
#line 71
    }
#line 71
}
#line 71
# 185 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
static inline void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$signalDone(void)
#line 185
{
  /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$SpiPacket$sendDone(/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_client, /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_tx_buf, /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_rx_buf, /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_len, 
  SUCCESS);
}

#line 168
static inline  void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$signalDone_task$runTask(void)
#line 168
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 169
    /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$signalDone();
#line 169
    __nesc_atomic_end(__nesc_atomic); }
}

# 124 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpiP.nc"
static inline stm25p_len_t Stm25pSpiP$calcReadLen(void)
#line 124
{
  return Stm25pSpiP$m_cur_len < Stm25pSpiP$CRC_BUF_SIZE ? Stm25pSpiP$m_cur_len : Stm25pSpiP$CRC_BUF_SIZE;
}

# 152 "/opt/tinyos-2.x/tos/lib/timer/VirtualizeTimerC.nc"
static inline  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer$stop(uint8_t num)
{
  /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$m_timers[num].isrunning = FALSE;
}

# 67 "/opt/tinyos-2.x/tos/lib/timer/Timer.nc"
inline static  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$TimerMilli$stop(void){
#line 67
  /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer$stop(0U);
#line 67
}
#line 67
# 69 "/opt/tinyos-2.x/tos/lib/power/DeferredPowerManagerP.nc"
static inline  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$startTask$runTask(void)
#line 69
{
  /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$TimerMilli$stop();
  /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$stopTimer = FALSE;
  /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$StdControl$start();
  /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$SplitControl$start();
}

# 53 "/opt/tinyos-2.x/tos/lib/timer/Counter.nc"
inline static   /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Counter$size_type /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Counter$get(void){
#line 53
  unsigned long result;
#line 53

#line 53
  result = /*CounterMilli32C.Transform*/TransformCounterC$0$Counter$get();
#line 53

#line 53
  return result;
#line 53
}
#line 53
# 75 "/opt/tinyos-2.x/tos/lib/timer/TransformAlarmC.nc"
static inline   /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$getNow(void)
{
  return /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Counter$get();
}

# 98 "/opt/tinyos-2.x/tos/lib/timer/Alarm.nc"
inline static   /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$size_type /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$getNow(void){
#line 98
  unsigned long result;
#line 98

#line 98
  result = /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$getNow();
#line 98

#line 98
  return result;
#line 98
}
#line 98
# 85 "/opt/tinyos-2.x/tos/lib/timer/AlarmToTimerC.nc"
static inline  uint32_t /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Timer$getNow(void)
{
#line 86
  return /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$getNow();
}

# 125 "/opt/tinyos-2.x/tos/lib/timer/Timer.nc"
inline static  uint32_t /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$TimerFrom$getNow(void){
#line 125
  unsigned long result;
#line 125

#line 125
  result = /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Timer$getNow();
#line 125

#line 125
  return result;
#line 125
}
#line 125
# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
inline static   error_t /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$updateFromTimer$postTask(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = SchedulerBasicP$TaskBasic$postTask(/*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$updateFromTimer);
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 132 "/opt/tinyos-2.x/tos/lib/timer/VirtualizeTimerC.nc"
static inline void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$startTimer(uint8_t num, uint32_t t0, uint32_t dt, bool isoneshot)
{
  /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer_t *timer = &/*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$m_timers[num];

#line 135
  timer->t0 = t0;
  timer->dt = dt;
  timer->isoneshot = isoneshot;
  timer->isrunning = TRUE;
  /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$updateFromTimer$postTask();
}






static inline  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer$startOneShot(uint8_t num, uint32_t dt)
{
  /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$startTimer(num, /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$TimerFrom$getNow(), dt, TRUE);
}

# 62 "/opt/tinyos-2.x/tos/lib/timer/Timer.nc"
inline static  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$TimerMilli$startOneShot(uint32_t arg_0x408dc490){
#line 62
  /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer$startOneShot(0U, arg_0x408dc490);
#line 62
}
#line 62
# 76 "/opt/tinyos-2.x/tos/lib/power/DeferredPowerManagerP.nc"
static inline  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$timerTask$runTask(void)
#line 76
{
  /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$TimerMilli$startOneShot(1024);
}

# 92 "/opt/tinyos-2.x/tos/lib/timer/Alarm.nc"
inline static   void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$startAt(/*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$size_type arg_0x408f4c30, /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$size_type arg_0x408f4db8){
#line 92
  /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$startAt(arg_0x408f4c30, arg_0x408f4db8);
#line 92
}
#line 92
# 47 "/opt/tinyos-2.x/tos/lib/timer/AlarmToTimerC.nc"
static inline void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$start(uint32_t t0, uint32_t dt, bool oneshot)
{
  /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$m_dt = dt;
  /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$m_oneshot = oneshot;
  /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$startAt(t0, dt);
}

#line 82
static inline  void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Timer$startOneShotAt(uint32_t t0, uint32_t dt)
{
#line 83
  /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$start(t0, dt, TRUE);
}

# 118 "/opt/tinyos-2.x/tos/lib/timer/Timer.nc"
inline static  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$TimerFrom$startOneShotAt(uint32_t arg_0x408d90c0, uint32_t arg_0x408d9248){
#line 118
  /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Timer$startOneShotAt(arg_0x408d90c0, arg_0x408d9248);
#line 118
}
#line 118
# 54 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430AlarmC.nc"
static inline   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Alarm$stop(void)
{
  /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430TimerControl$disableEvents();
}

# 62 "/opt/tinyos-2.x/tos/lib/timer/Alarm.nc"
inline static   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$AlarmFrom$stop(void){
#line 62
  /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Alarm$stop();
#line 62
}
#line 62
# 91 "/opt/tinyos-2.x/tos/lib/timer/TransformAlarmC.nc"
static inline   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$stop(void)
{
  /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$AlarmFrom$stop();
}

# 62 "/opt/tinyos-2.x/tos/lib/timer/Alarm.nc"
inline static   void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$stop(void){
#line 62
  /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$stop();
#line 62
}
#line 62
# 60 "/opt/tinyos-2.x/tos/lib/timer/AlarmToTimerC.nc"
static inline  void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Timer$stop(void)
{
#line 61
  /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$stop();
}

# 67 "/opt/tinyos-2.x/tos/lib/timer/Timer.nc"
inline static  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$TimerFrom$stop(void){
#line 67
  /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Timer$stop();
#line 67
}
#line 67
# 88 "/opt/tinyos-2.x/tos/lib/timer/VirtualizeTimerC.nc"
static inline  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$updateFromTimer$runTask(void)
{




  uint32_t now = /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$TimerFrom$getNow();
  int32_t min_remaining = (1UL << 31) - 1;
  bool min_remaining_isset = FALSE;
  uint8_t num;

  /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$TimerFrom$stop();

  for (num = 0; num < /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$NUM_TIMERS; num++) 
    {
      /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer_t *timer = &/*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$m_timers[num];

      if (timer->isrunning) 
        {
          uint32_t elapsed = now - timer->t0;
          int32_t remaining = timer->dt - elapsed;

          if (remaining < min_remaining) 
            {
              min_remaining = remaining;
              min_remaining_isset = TRUE;
            }
        }
    }

  if (min_remaining_isset) 
    {
      if (min_remaining <= 0) {
        /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$fireTimers(now);
        }
      else {
#line 123
        /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$TimerFrom$startOneShotAt(now, min_remaining);
        }
    }
}

# 111 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
static inline    error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$default$request(uint8_t id)
#line 111
{
#line 111
  return FAIL;
}

# 78 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$request(uint8_t arg_0x40a7de58){
#line 78
  unsigned char result;
#line 78

#line 78
  switch (arg_0x40a7de58) {
#line 78
    case /*HplStm25pSpiC.SpiC*/Msp430Spi0C$0$CLIENT_ID:
#line 78
      result = /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Resource$request(/*HplStm25pSpiC.SpiC.UsartC*/Msp430Usart0C$0$CLIENT_ID);
#line 78
      break;
#line 78
    default:
#line 78
      result = /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$default$request(arg_0x40a7de58);
#line 78
      break;
#line 78
    }
#line 78

#line 78
  return result;
#line 78
}
#line 78
# 72 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
static inline   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Resource$request(uint8_t id)
#line 72
{
  return /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartResource$request(id);
}

# 78 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static   error_t Stm25pSpiP$SpiResource$request(void){
#line 78
  unsigned char result;
#line 78

#line 78
  result = /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Resource$request(/*HplStm25pSpiC.SpiC*/Msp430Spi0C$0$CLIENT_ID);
#line 78

#line 78
  return result;
#line 78
}
#line 78
# 108 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpiP.nc"
static inline   error_t Stm25pSpiP$ClientResource$request(void)
#line 108
{
  return Stm25pSpiP$SpiResource$request();
}

# 78 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static   error_t Stm25pSectorP$SpiResource$request(void){
#line 78
  unsigned char result;
#line 78

#line 78
  result = Stm25pSpiP$ClientResource$request();
#line 78

#line 78
  return result;
#line 78
}
#line 78
# 95 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline  error_t Stm25pSectorP$SplitControl$stop(void)
#line 95
{
  error_t error = Stm25pSectorP$SpiResource$request();

#line 97
  if (error == SUCCESS) {
    Stm25pSectorP$m_power_state = Stm25pSectorP$S_STOP;
    }
#line 99
  return error;
}

# 109 "/opt/tinyos-2.x/tos/interfaces/SplitControl.nc"
inline static  error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$SplitControl$stop(void){
#line 109
  unsigned char result;
#line 109

#line 109
  result = Stm25pSectorP$SplitControl$stop();
#line 109

#line 109
  return result;
#line 109
}
#line 109
# 127 "/opt/tinyos-2.x/tos/lib/power/DeferredPowerManagerP.nc"
static inline   error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$StdControl$default$stop(void)
#line 127
{
  return SUCCESS;
}

# 84 "/opt/tinyos-2.x/tos/interfaces/StdControl.nc"
inline static  error_t /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$StdControl$stop(void){
#line 84
  unsigned char result;
#line 84

#line 84
  result = /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$StdControl$default$stop();
#line 84

#line 84
  return result;
#line 84
}
#line 84
# 135 "/opt/tinyos-2.x/tos/lib/power/DeferredPowerManagerP.nc"
static inline    void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$PowerDownCleanup$default$cleanup(void)
#line 135
{
}

# 52 "/opt/tinyos-2.x/tos/lib/power/PowerDownCleanup.nc"
inline static   void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$PowerDownCleanup$cleanup(void){
#line 52
  /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$PowerDownCleanup$default$cleanup();
#line 52
}
#line 52
# 107 "/opt/tinyos-2.x/tos/lib/power/DeferredPowerManagerP.nc"
static inline  void /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$TimerMilli$fired(void)
#line 107
{
  if (/*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$stopTimer == FALSE) {
      /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$stopping = TRUE;
      /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$PowerDownCleanup$cleanup();
      /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$StdControl$stop();
      /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$SplitControl$stop();
    }
}

# 192 "/opt/tinyos-2.x/tos/lib/timer/VirtualizeTimerC.nc"
static inline   void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer$default$fired(uint8_t num)
{
}

# 72 "/opt/tinyos-2.x/tos/lib/timer/Timer.nc"
inline static  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer$fired(uint8_t arg_0x409ae088){
#line 72
  switch (arg_0x409ae088) {
#line 72
    case 0U:
#line 72
      /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$TimerMilli$fired();
#line 72
      break;
#line 72
    default:
#line 72
      /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer$default$fired(arg_0x409ae088);
#line 72
      break;
#line 72
    }
#line 72
}
#line 72
# 127 "/opt/tinyos-2.x/tos/lib/timer/VirtualizeTimerC.nc"
static inline  void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$TimerFrom$fired(void)
{
  /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$fireTimers(/*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$TimerFrom$getNow());
}

# 72 "/opt/tinyos-2.x/tos/lib/timer/Timer.nc"
inline static  void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Timer$fired(void){
#line 72
  /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$TimerFrom$fired();
#line 72
}
#line 72
# 80 "/opt/tinyos-2.x/tos/lib/timer/TransformAlarmC.nc"
static inline   /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$getAlarm(void)
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 82
    {
      /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type __nesc_temp = 
#line 82
      /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_t0 + /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_dt;

      {
#line 82
        __nesc_atomic_end(__nesc_atomic); 
#line 82
        return __nesc_temp;
      }
    }
#line 84
    __nesc_atomic_end(__nesc_atomic); }
}

# 105 "/opt/tinyos-2.x/tos/lib/timer/Alarm.nc"
inline static   /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$size_type /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$getAlarm(void){
#line 105
  unsigned long result;
#line 105

#line 105
  result = /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$getAlarm();
#line 105

#line 105
  return result;
#line 105
}
#line 105
# 63 "/opt/tinyos-2.x/tos/lib/timer/AlarmToTimerC.nc"
static inline  void /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$fired$runTask(void)
{
  if (/*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$m_oneshot == FALSE) {
    /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$start(/*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Alarm$getAlarm(), /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$m_dt, FALSE);
    }
#line 67
  /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$Timer$fired();
}

# 121 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline  void Stm25pSectorP$Stm25pResource$granted(uint8_t id)
#line 121
{
  Stm25pSectorP$m_client = id;
  Stm25pSectorP$SpiResource$request();
}

# 92 "/opt/tinyos-2.x/tos/interfaces/Resource.nc"
inline static  void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Resource$granted(uint8_t arg_0x4089ff00){
#line 92
  Stm25pSectorP$Stm25pResource$granted(arg_0x4089ff00);
#line 92
}
#line 92
# 208 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline    void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceConfigure$default$configure(uint8_t id)
#line 208
{
}

# 49 "/opt/tinyos-2.x/tos/interfaces/ResourceConfigure.nc"
inline static   void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceConfigure$configure(uint8_t arg_0x4089c9b8){
#line 49
    /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceConfigure$default$configure(arg_0x4089c9b8);
#line 49
}
#line 49
# 182 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static inline  void /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$grantedTask$runTask(void)
#line 182
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 183
    {
      /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$resId = /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$reqResId;
      /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$state = /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$RES_BUSY;
    }
#line 186
    __nesc_atomic_end(__nesc_atomic); }
  /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$ResourceConfigure$configure(/*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$resId);
  /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$Resource$granted(/*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$resId);
}

# 523 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline   error_t Stm25pLogP$Sector$default$write(uint8_t id, storage_addr_t addr, uint8_t *buf, storage_len_t len)
#line 523
{
#line 523
  return FAIL;
}

# 91 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
inline static  error_t Stm25pLogP$Sector$write(uint8_t arg_0x407a0010, stm25p_addr_t arg_0x407b3768, uint8_t *arg_0x407b3908, stm25p_len_t arg_0x407b3a90){
#line 91
  unsigned char result;
#line 91

#line 91
  switch (arg_0x407a0010) {
#line 91
    case /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$LOG_ID:
#line 91
      result = Stm25pSectorP$Sector$write(/*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$VOLUME_ID, arg_0x407b3768, arg_0x407b3908, arg_0x407b3a90);
#line 91
      break;
#line 91
    default:
#line 91
      result = Stm25pLogP$Sector$default$write(arg_0x407a0010, arg_0x407b3768, arg_0x407b3908, arg_0x407b3a90);
#line 91
      break;
#line 91
    }
#line 91

#line 91
  return result;
#line 91
}
#line 91
# 444 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline  void Stm25pLogP$Sector$eraseDone(uint8_t id, uint8_t sector, 
uint8_t num_sectors, 
error_t error)
#line 446
{
  if (Stm25pLogP$m_log_state[id].req == Stm25pLogP$S_ERASE) {
      Stm25pLogP$m_log_info[id].read_addr = 0;
      Stm25pLogP$m_log_info[id].write_addr = 0;
      Stm25pLogP$signalDone(id, error);
    }
  else {


      stm25p_addr_t volume_size = 
      STM25P_SECTOR_SIZE * (Stm25pLogP$Sector$getNumSectors(id) - 1);

#line 457
      if (Stm25pLogP$m_log_info[id].write_addr > volume_size) {
          stm25p_addr_t read_addr = Stm25pLogP$m_log_info[id].write_addr - volume_size;

#line 459
          if (Stm25pLogP$m_log_info[id].read_addr < read_addr) {
            Stm25pLogP$m_log_info[id].read_addr = read_addr;
            }
        }
#line 462
      Stm25pLogP$m_addr = Stm25pLogP$m_log_info[id].write_addr;
      Stm25pLogP$Sector$write(id, Stm25pLogP$calcAddr(id, Stm25pLogP$m_addr), (uint8_t *)&Stm25pLogP$m_addr, 
      sizeof Stm25pLogP$m_addr);
    }
}

# 287 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline   void Stm25pSectorP$Sector$default$eraseDone(uint8_t id, uint8_t sector, uint8_t num_sectors, error_t error)
#line 287
{
}

# 121 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
inline static  void Stm25pSectorP$Sector$eraseDone(uint8_t arg_0x4082a958, uint8_t arg_0x407b0250, uint8_t arg_0x407b03d8, error_t arg_0x407b0558){
#line 121
  switch (arg_0x4082a958) {
#line 121
    case /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$VOLUME_ID:
#line 121
      Stm25pLogP$Sector$eraseDone(/*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$LOG_ID, arg_0x407b0250, arg_0x407b03d8, arg_0x407b0558);
#line 121
      break;
#line 121
    default:
#line 121
      Stm25pSectorP$Sector$default$eraseDone(arg_0x4082a958, arg_0x407b0250, arg_0x407b03d8, arg_0x407b0558);
#line 121
      break;
#line 121
    }
#line 121
}
#line 121
# 468 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline  void Stm25pLogP$Sector$writeDone(uint8_t id, storage_addr_t addr, 
uint8_t *buf, 
storage_len_t len, 
error_t error)
#line 471
{
  Stm25pLogP$m_log_info[id].write_addr += len;
  if (Stm25pLogP$m_rw_state == Stm25pLogP$S_HEADER) {
      if (len == sizeof Stm25pLogP$m_header) {
        Stm25pLogP$m_rw_state = Stm25pLogP$S_DATA;
        }
#line 476
      Stm25pLogP$continueAppendOp(id);
    }
  else {
      Stm25pLogP$signalDone(id, error);
    }
}

# 286 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline   void Stm25pSectorP$Sector$default$writeDone(uint8_t id, stm25p_addr_t addr, uint8_t *buf, stm25p_len_t len, error_t error)
#line 286
{
}

# 101 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
inline static  void Stm25pSectorP$Sector$writeDone(uint8_t arg_0x4082a958, stm25p_addr_t arg_0x407b1088, uint8_t *arg_0x407b1228, stm25p_len_t arg_0x407b13b0, error_t arg_0x407b1540){
#line 101
  switch (arg_0x4082a958) {
#line 101
    case /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$VOLUME_ID:
#line 101
      Stm25pLogP$Sector$writeDone(/*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$LOG_ID, arg_0x407b1088, arg_0x407b1228, arg_0x407b13b0, arg_0x407b1540);
#line 101
      break;
#line 101
    default:
#line 101
      Stm25pSectorP$Sector$default$writeDone(arg_0x4082a958, arg_0x407b1088, arg_0x407b1228, arg_0x407b13b0, arg_0x407b1540);
#line 101
      break;
#line 101
    }
#line 101
}
#line 101
# 512 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline  void Stm25pLogP$Sector$computeCrcDone(uint8_t id, stm25p_addr_t addr, stm25p_len_t len, uint16_t crc, error_t error)
#line 512
{
}

# 288 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline   void Stm25pSectorP$Sector$default$computeCrcDone(uint8_t id, stm25p_addr_t addr, stm25p_len_t len, uint16_t crc, error_t error)
#line 288
{
}

# 144 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
inline static  void Stm25pSectorP$Sector$computeCrcDone(uint8_t arg_0x4082a958, stm25p_addr_t arg_0x407af4e8, stm25p_len_t arg_0x407af670, uint16_t arg_0x407af808, error_t arg_0x407af988){
#line 144
  switch (arg_0x4082a958) {
#line 144
    case /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$VOLUME_ID:
#line 144
      Stm25pLogP$Sector$computeCrcDone(/*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$LOG_ID, arg_0x407af4e8, arg_0x407af670, arg_0x407af808, arg_0x407af988);
#line 144
      break;
#line 144
    default:
#line 144
      Stm25pSectorP$Sector$default$computeCrcDone(arg_0x4082a958, arg_0x407af4e8, arg_0x407af670, arg_0x407af808, arg_0x407af988);
#line 144
      break;
#line 144
    }
#line 144
}
#line 144
# 522 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline   error_t Stm25pLogP$Sector$default$read(uint8_t id, storage_addr_t addr, uint8_t *buf, storage_len_t len)
#line 522
{
#line 522
  return FAIL;
}

# 68 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
inline static  error_t Stm25pLogP$Sector$read(uint8_t arg_0x407a0010, stm25p_addr_t arg_0x4079a330, uint8_t *arg_0x4079a4d0, stm25p_len_t arg_0x4079a658){
#line 68
  unsigned char result;
#line 68

#line 68
  switch (arg_0x407a0010) {
#line 68
    case /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$LOG_ID:
#line 68
      result = Stm25pSectorP$Sector$read(/*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$VOLUME_ID, arg_0x4079a330, arg_0x4079a4d0, arg_0x4079a658);
#line 68
      break;
#line 68
    default:
#line 68
      result = Stm25pLogP$Sector$default$read(arg_0x407a0010, arg_0x4079a330, arg_0x4079a4d0, arg_0x4079a658);
#line 68
      break;
#line 68
    }
#line 68

#line 68
  return result;
#line 68
}
#line 68
# 311 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline  void Stm25pLogP$Sector$readDone(uint8_t id, stm25p_addr_t addr, uint8_t *buf, 
stm25p_len_t len, error_t error)
#line 312
{

  Stm25pLogP$stm25p_log_info_t *log_info = &Stm25pLogP$m_log_info[id];


  switch (Stm25pLogP$m_rw_state) {
      case Stm25pLogP$S_SEARCH_BLOCKS: 
        {
          uint16_t block = addr >> Stm25pLogP$BLOCK_SIZE_LOG2;

          if (Stm25pLogP$m_addr != STM25P_INVALID_ADDRESS) {
              if (Stm25pLogP$m_addr < log_info->read_addr) {
                log_info->read_addr = Stm25pLogP$m_addr;
                }
#line 325
              if (Stm25pLogP$m_addr > log_info->write_addr) {
                log_info->write_addr = Stm25pLogP$m_addr;
                }
            }
          if (++block < Stm25pLogP$Sector$getNumSectors(id) * Stm25pLogP$BLOCKS_PER_SECTOR) {
              addr += Stm25pLogP$BLOCK_SIZE;
              Stm25pLogP$Sector$read(id, addr, (uint8_t *)&Stm25pLogP$m_addr, sizeof Stm25pLogP$m_addr);
            }
          else {
            if (log_info->read_addr == STM25P_INVALID_ADDRESS) {
                log_info->read_addr = 0;
                log_info->write_addr = 0;
                Stm25pLogP$ClientResource$granted(id);
              }
            else 
              {
                log_info->write_addr += sizeof Stm25pLogP$m_addr;
                Stm25pLogP$m_rw_state = Stm25pLogP$S_SEARCH_RECORDS;
                Stm25pLogP$Sector$read(id, log_info->write_addr, &Stm25pLogP$m_header, 
                sizeof Stm25pLogP$m_header);
              }
            }
        }
#line 347
      break;

      case Stm25pLogP$S_SEARCH_RECORDS: 
        {

          uint16_t cur_block = log_info->write_addr >> Stm25pLogP$BLOCK_SIZE_LOG2;
          uint16_t new_block = (log_info->write_addr + sizeof Stm25pLogP$m_header + 
          Stm25pLogP$m_header) >> Stm25pLogP$BLOCK_SIZE_LOG2;

          if (Stm25pLogP$m_header != Stm25pLogP$INVALID_HEADER && cur_block == new_block) {
              log_info->write_addr += sizeof Stm25pLogP$m_header + Stm25pLogP$m_header;
              Stm25pLogP$Sector$read(id, Stm25pLogP$calcAddr(id, log_info->write_addr), 
              &Stm25pLogP$m_header, sizeof Stm25pLogP$m_header);
            }
          else 
            {
              Stm25pLogP$ClientResource$granted(id);
            }
        }
      break;

      case Stm25pLogP$S_SEARCH_SEEK: 
        {

          log_info->read_addr += sizeof Stm25pLogP$m_header + Stm25pLogP$m_header;

          if (log_info->read_addr < Stm25pLogP$m_log_state[id].cookie) {
              Stm25pLogP$Sector$read(id, log_info->read_addr, &Stm25pLogP$m_header, 
              sizeof Stm25pLogP$m_header);
            }
          else 
            {
              log_info->remaining = log_info->read_addr - Stm25pLogP$m_log_state[id].cookie;
              log_info->read_addr = Stm25pLogP$m_log_state[id].cookie;
              Stm25pLogP$signalDone(id, error);
            }
        }
      break;

      case Stm25pLogP$S_HEADER: 
        {

          if (Stm25pLogP$m_header == Stm25pLogP$INVALID_HEADER) {
              log_info->read_addr += Stm25pLogP$BLOCK_SIZE;
              log_info->read_addr &= ~Stm25pLogP$BLOCK_MASK;
            }
          else {
              log_info->read_addr += sizeof Stm25pLogP$m_header;
              log_info->remaining = Stm25pLogP$m_header;
              Stm25pLogP$m_rw_state = Stm25pLogP$S_DATA;
            }
          Stm25pLogP$continueReadOp(id);
        }
      break;

      case Stm25pLogP$S_DATA: 
        {
          log_info->read_addr += len;
          log_info->remaining -= len;
          Stm25pLogP$m_len -= len;
          Stm25pLogP$m_rw_state = Stm25pLogP$S_HEADER;
          Stm25pLogP$continueReadOp(id);
          break;
        }
    }
}

# 285 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline   void Stm25pSectorP$Sector$default$readDone(uint8_t id, stm25p_addr_t addr, uint8_t *buf, stm25p_len_t len, error_t error)
#line 285
{
}

# 78 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSector.nc"
inline static  void Stm25pSectorP$Sector$readDone(uint8_t arg_0x4082a958, stm25p_addr_t arg_0x4079ac48, uint8_t *arg_0x4079ade8, stm25p_len_t arg_0x407b3010, error_t arg_0x407b31a0){
#line 78
  switch (arg_0x4082a958) {
#line 78
    case /*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$VOLUME_ID:
#line 78
      Stm25pLogP$Sector$readDone(/*TraceRetrieverAppC.TraceRetrieverC.LogStorageC*/LogStorageC$0$LOG_ID, arg_0x4079ac48, arg_0x4079ade8, arg_0x407b3010, arg_0x407b31a0);
#line 78
      break;
#line 78
    default:
#line 78
      Stm25pSectorP$Sector$default$readDone(arg_0x4082a958, arg_0x4079ac48, arg_0x4079ade8, arg_0x407b3010, arg_0x407b31a0);
#line 78
      break;
#line 78
    }
#line 78
}
#line 78
# 261 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static inline  void Stm25pSectorP$signalDone_task$runTask(void)
#line 261
{
  switch (Stm25pSectorP$m_state) {
      case Stm25pSectorP$S_IDLE: 
        Stm25pSectorP$ClientResource$granted(Stm25pSectorP$m_client);
      break;
      case Stm25pSectorP$S_READ: 
        Stm25pSectorP$Sector$readDone(Stm25pSectorP$m_client, Stm25pSectorP$m_addr, Stm25pSectorP$m_buf, Stm25pSectorP$m_len, Stm25pSectorP$m_error);
      break;
      case Stm25pSectorP$S_CRC: 
        Stm25pSectorP$Sector$computeCrcDone(Stm25pSectorP$m_client, Stm25pSectorP$m_addr, Stm25pSectorP$m_len, 
        Stm25pSectorP$m_crc, Stm25pSectorP$m_error);
      break;
      case Stm25pSectorP$S_WRITE: 
        Stm25pSectorP$Sector$writeDone(Stm25pSectorP$m_client, Stm25pSectorP$m_addr, Stm25pSectorP$m_buf, Stm25pSectorP$m_len, Stm25pSectorP$m_error);
      break;
      case Stm25pSectorP$S_ERASE: 
        Stm25pSectorP$Sector$eraseDone(Stm25pSectorP$m_client, Stm25pSectorP$m_addr, Stm25pSectorP$m_len, Stm25pSectorP$m_error);
      break;
      default: 
        break;
    }
}

# 49 "/opt/tinyos-2.x/tos/types/TinyError.h"
static inline error_t ecombine(error_t r1, error_t r2)




{
  return r1 == r2 ? r1 : FAIL;
}

# 109 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static inline  error_t Stm25pLogP$Init$init(void)
#line 109
{
  int i;

#line 111
  for (i = 0; i < Stm25pLogP$NUM_LOGS; i++) {
      Stm25pLogP$m_log_info[i].read_addr = STM25P_INVALID_ADDRESS;
      Stm25pLogP$m_log_info[i].write_addr = 0;
    }
  return SUCCESS;
}

# 45 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P47*/HplMsp430GeneralIOP$31$IO$set(void)
#line 45
{
  /* atomic removed: atomic calls only */
#line 45
  * (volatile uint8_t *)29U |= 0x01 << 7;
}

# 34 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void /*HplStm25pPinsC.HoldM*/Msp430GpioC$4$HplGeneralIO$set(void){
#line 34
  /*HplMsp430GeneralIOC.P47*/HplMsp430GeneralIOP$31$IO$set();
#line 34
}
#line 34
# 37 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*HplStm25pPinsC.HoldM*/Msp430GpioC$4$GeneralIO$set(void)
#line 37
{
#line 37
  /*HplStm25pPinsC.HoldM*/Msp430GpioC$4$HplGeneralIO$set();
}

# 29 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
inline static   void Stm25pSpiP$Hold$set(void){
#line 29
  /*HplStm25pPinsC.HoldM*/Msp430GpioC$4$GeneralIO$set();
#line 29
}
#line 29
# 34 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$HplGeneralIO$set(void){
#line 34
  /*HplMsp430GeneralIOC.P44*/HplMsp430GeneralIOP$28$IO$set();
#line 34
}
#line 34
# 37 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$GeneralIO$set(void)
#line 37
{
#line 37
  /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$HplGeneralIO$set();
}

# 29 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
inline static   void Stm25pSpiP$CSN$set(void){
#line 29
  /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$GeneralIO$set();
#line 29
}
#line 29
# 52 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P47*/HplMsp430GeneralIOP$31$IO$makeOutput(void)
#line 52
{
  /* atomic removed: atomic calls only */
#line 52
  * (volatile uint8_t *)30U |= 0x01 << 7;
}

# 71 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void /*HplStm25pPinsC.HoldM*/Msp430GpioC$4$HplGeneralIO$makeOutput(void){
#line 71
  /*HplMsp430GeneralIOC.P47*/HplMsp430GeneralIOP$31$IO$makeOutput();
#line 71
}
#line 71
# 43 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*HplStm25pPinsC.HoldM*/Msp430GpioC$4$GeneralIO$makeOutput(void)
#line 43
{
#line 43
  /*HplStm25pPinsC.HoldM*/Msp430GpioC$4$HplGeneralIO$makeOutput();
}

# 35 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
inline static   void Stm25pSpiP$Hold$makeOutput(void){
#line 35
  /*HplStm25pPinsC.HoldM*/Msp430GpioC$4$GeneralIO$makeOutput();
#line 35
}
#line 35
# 52 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static inline   void /*HplMsp430GeneralIOC.P44*/HplMsp430GeneralIOP$28$IO$makeOutput(void)
#line 52
{
  /* atomic removed: atomic calls only */
#line 52
  * (volatile uint8_t *)30U |= 0x01 << 4;
}

# 71 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIO.nc"
inline static   void /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$HplGeneralIO$makeOutput(void){
#line 71
  /*HplMsp430GeneralIOC.P44*/HplMsp430GeneralIOP$28$IO$makeOutput();
#line 71
}
#line 71
# 43 "/opt/tinyos-2.x/tos/chips/msp430/pins/Msp430GpioC.nc"
static inline   void /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$GeneralIO$makeOutput(void)
#line 43
{
#line 43
  /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$HplGeneralIO$makeOutput();
}

# 35 "/opt/tinyos-2.x/tos/interfaces/GeneralIO.nc"
inline static   void Stm25pSpiP$CSN$makeOutput(void){
#line 35
  /*HplStm25pPinsC.CSNM*/Msp430GpioC$3$GeneralIO$makeOutput();
#line 35
}
#line 35
# 100 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpiP.nc"
static inline  error_t Stm25pSpiP$Init$init(void)
#line 100
{
  Stm25pSpiP$CSN$makeOutput();
  Stm25pSpiP$Hold$makeOutput();
  Stm25pSpiP$CSN$set();
  Stm25pSpiP$Hold$set();
  return SUCCESS;
}

# 45 "/opt/tinyos-2.x/tos/system/FcfsResourceQueueC.nc"
static inline  error_t /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$Init$init(void)
#line 45
{
  memset(/*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$resQ, /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$NO_ENTRY, sizeof /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$resQ);
  return SUCCESS;
}

# 46 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static inline uint16_t /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$CC2int(/*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$cc_t x)
#line 46
{
#line 46
  union  {
#line 46
    /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$cc_t f;
#line 46
    uint16_t t;
  } 
#line 46
  c = { .f = x };

#line 46
  return c.t;
}

static inline uint16_t /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$compareControl(void)
{
  /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$cc_t x = { 
  .cm = 1, 
  .ccis = 0, 
  .clld = 0, 
  .cap = 0, 
  .ccie = 0 };

  return /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$CC2int(x);
}

#line 94
static inline   void /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$setControlAsCompare(void)
{
  * (volatile uint16_t *)386U = /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$compareControl();
}

# 36 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerControl.nc"
inline static   void /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430TimerControl$setControlAsCompare(void){
#line 36
  /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Control$setControlAsCompare();
#line 36
}
#line 36
# 42 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430AlarmC.nc"
static inline  error_t /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Init$init(void)
{
  /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430TimerControl$disableEvents();
  /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Msp430TimerControl$setControlAsCompare();
  return SUCCESS;
}

# 45 "/opt/tinyos-2.x/tos/system/FcfsResourceQueueC.nc"
static inline  error_t /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$Init$init(void)
#line 45
{
  memset(/*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$resQ, /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$NO_ENTRY, sizeof /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$resQ);
  return SUCCESS;
}

# 214 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static __inline void SerialP$ackInit(void)
#line 214
{
  SerialP$ackQ.writePtr = SerialP$ackQ.readPtr = 0;
}

#line 205
static __inline void SerialP$rxInit(void)
#line 205
{
  SerialP$rxBuf.writePtr = SerialP$rxBuf.readPtr = 0;
  SerialP$rxState = SerialP$RXSTATE_NOSYNC;
  SerialP$rxByteCnt = 0;
  SerialP$rxProto = 0;
  SerialP$rxSeqno = 0;
  SerialP$rxCRC = 0;
}

#line 193
static __inline void SerialP$txInit(void)
#line 193
{
  uint8_t i;

  /* atomic removed: atomic calls only */
#line 195
  for (i = 0; i < SerialP$TX_BUFFER_COUNT; i++) SerialP$txBuf[i].state = SerialP$BUFFER_AVAILABLE;
  SerialP$txState = SerialP$TXSTATE_IDLE;
  SerialP$txByteCnt = 0;
  SerialP$txProto = 0;
  SerialP$txSeqno = 0;
  SerialP$txCRC = 0;
  SerialP$txPending = FALSE;
  SerialP$txIndex = 0;
}

#line 218
static inline  error_t SerialP$Init$init(void)
#line 218
{

  SerialP$txInit();
  SerialP$rxInit();
  SerialP$ackInit();

  return SUCCESS;
}

# 45 "/opt/tinyos-2.x/tos/system/FcfsResourceQueueC.nc"
static inline  error_t /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$Init$init(void)
#line 45
{
  memset(/*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$resQ, /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$NO_ENTRY, sizeof /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$resQ);
  return SUCCESS;
}

# 51 "/opt/tinyos-2.x/tos/interfaces/Init.nc"
inline static  error_t RealMainP$SoftwareInit$init(void){
#line 51
  unsigned char result;
#line 51

#line 51
  result = /*Msp430UsartShare1P.ArbiterC.Queue*/FcfsResourceQueueC$2$Init$init();
#line 51
  result = ecombine(result, SerialP$Init$init());
#line 51
  result = ecombine(result, /*Msp430UsartShare0P.ArbiterC.Queue*/FcfsResourceQueueC$1$Init$init());
#line 51
  result = ecombine(result, /*HilTimerMilliC.AlarmMilli32C.AlarmFrom.Msp430Alarm*/Msp430AlarmC$0$Init$init());
#line 51
  result = ecombine(result, /*Stm25pSectorC.ArbiterC.Queue*/FcfsResourceQueueC$0$Init$init());
#line 51
  result = ecombine(result, Stm25pSpiP$Init$init());
#line 51
  result = ecombine(result, Stm25pLogP$Init$init());
#line 51

#line 51
  return result;
#line 51
}
#line 51
# 110 "/opt/tinyos-2.x/tos/lib/serial/SerialActiveMessageP.nc"
static inline  uint8_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Packet$maxPayloadLength(void)
#line 110
{
  return 28;
}

static inline  void */*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Packet$getPayload(message_t *msg, uint8_t len)
#line 114
{
  if (len > /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Packet$maxPayloadLength()) {
      return (void *)0;
    }
  else {
      return msg->data;
    }
}

# 106 "/opt/tinyos-2.x/tos/interfaces/Packet.nc"
inline static  void *TraceRetrieverP$Packet$getPayload(message_t *arg_0x406513b8, uint8_t arg_0x40651538){
#line 106
  void *result;
#line 106

#line 106
  result = /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$Packet$getPayload(arg_0x406513b8, arg_0x40651538);
#line 106

#line 106
  return result;
#line 106
}
#line 106
# 57 "TraceRetrieverP.nc"
static inline  void TraceRetrieverP$Boot$booted(void)
#line 57
{
  TraceRetrieverP$reading = TRUE;
  TraceRetrieverP$logEntry = (TraceEntry *)TraceRetrieverP$Packet$getPayload(&TraceRetrieverP$logData, sizeof(TraceEntry ));

  TraceRetrieverP$AMControl$start();
}

# 49 "/opt/tinyos-2.x/tos/interfaces/Boot.nc"
inline static  void RealMainP$Boot$booted(void){
#line 49
  TraceRetrieverP$Boot$booted();
#line 49
}
#line 49
# 190 "/opt/tinyos-2.x/tos/chips/msp430/msp430hardware.h"
static inline void __nesc_disable_interrupt(void )
{
   __asm volatile ("dint");
   __asm volatile ("nop");}

# 124 "/opt/tinyos-2.x/tos/chips/msp430/McuSleepC.nc"
static inline    mcu_power_t McuSleepC$McuPowerOverride$default$lowestState(void)
#line 124
{
  return MSP430_POWER_LPM4;
}

# 54 "/opt/tinyos-2.x/tos/interfaces/McuPowerOverride.nc"
inline static   mcu_power_t McuSleepC$McuPowerOverride$lowestState(void){
#line 54
  unsigned char result;
#line 54

#line 54
  result = McuSleepC$McuPowerOverride$default$lowestState();
#line 54

#line 54
  return result;
#line 54
}
#line 54
# 66 "/opt/tinyos-2.x/tos/chips/msp430/McuSleepC.nc"
static inline mcu_power_t McuSleepC$getPowerState(void)
#line 66
{
  mcu_power_t pState = MSP430_POWER_LPM3;









  if ((((((
#line 69
  TA0CCTL0 & 0x0010 || 
  TA0CCTL1 & 0x0010) || 
  TA0CCTL2 & 0x0010) && (
  TA0CTL & (3 << 8)) == 2 << 8) || (
  ME1 & ((1 << 7) | (1 << 6)) && U0TCTL & 0x20)) || (
  ME2 & ((1 << 5) | (1 << 4)) && U1TCTL & 0x20))


   || (U0CTLnr & 0x01 && I2CTCTLnr & 0x20 && 
  I2CDCTLnr & 0x20 && U0CTLnr & 0x04 && U0CTLnr & 0x20)) {


    pState = MSP430_POWER_LPM1;
    }


  if (ADC12CTL0 & 0x0010) {
      if (ADC12CTL1 & (2 << 3)) {

          if (ADC12CTL1 & (1 << 3)) {
            pState = MSP430_POWER_LPM1;
            }
          else {
#line 91
            pState = MSP430_POWER_ACTIVE;
            }
        }
      else {
#line 92
        if (ADC12CTL1 & 0x0400 && (TA0CTL & (3 << 8)) == 2 << 8) {



            pState = MSP430_POWER_LPM1;
          }
        }
    }

  return pState;
}

# 178 "/opt/tinyos-2.x/tos/chips/msp430/msp430hardware.h"
static inline mcu_power_t mcombine(mcu_power_t m1, mcu_power_t m2)
#line 178
{
  return m1 < m2 ? m1 : m2;
}

# 104 "/opt/tinyos-2.x/tos/chips/msp430/McuSleepC.nc"
static inline void McuSleepC$computePowerState(void)
#line 104
{
  McuSleepC$powerState = mcombine(McuSleepC$getPowerState(), 
  McuSleepC$McuPowerOverride$lowestState());
}

static inline   void McuSleepC$McuSleep$sleep(void)
#line 109
{
  uint16_t temp;

#line 111
  if (McuSleepC$dirty) {
      McuSleepC$computePowerState();
    }

  temp = McuSleepC$msp430PowerBits[McuSleepC$powerState] | 0x0008;
   __asm volatile ("bis  %0, r2" :  : "m"(temp));
  __nesc_disable_interrupt();
}

# 59 "/opt/tinyos-2.x/tos/interfaces/McuSleep.nc"
inline static   void SchedulerBasicP$McuSleep$sleep(void){
#line 59
  McuSleepC$McuSleep$sleep();
#line 59
}
#line 59
# 67 "/opt/tinyos-2.x/tos/system/SchedulerBasicP.nc"
static __inline uint8_t SchedulerBasicP$popTask(void)
{
  if (SchedulerBasicP$m_head != SchedulerBasicP$NO_TASK) 
    {
      uint8_t id = SchedulerBasicP$m_head;

#line 72
      SchedulerBasicP$m_head = SchedulerBasicP$m_next[SchedulerBasicP$m_head];
      if (SchedulerBasicP$m_head == SchedulerBasicP$NO_TASK) 
        {
          SchedulerBasicP$m_tail = SchedulerBasicP$NO_TASK;
        }
      SchedulerBasicP$m_next[id] = SchedulerBasicP$NO_TASK;
      return id;
    }
  else 
    {
      return SchedulerBasicP$NO_TASK;
    }
}

#line 138
static inline  void SchedulerBasicP$Scheduler$taskLoop(void)
{
  for (; ; ) 
    {
      uint8_t nextTask;

      { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
        {
          while ((nextTask = SchedulerBasicP$popTask()) == SchedulerBasicP$NO_TASK) 
            {
              SchedulerBasicP$McuSleep$sleep();
            }
        }
#line 150
        __nesc_atomic_end(__nesc_atomic); }
      SchedulerBasicP$TaskBasic$runTask(nextTask);
    }
}

# 61 "/opt/tinyos-2.x/tos/interfaces/Scheduler.nc"
inline static  void RealMainP$Scheduler$taskLoop(void){
#line 61
  SchedulerBasicP$Scheduler$taskLoop();
#line 61
}
#line 61
# 88 "/opt/tinyos-2.x/tos/interfaces/ArbiterInfo.nc"
inline static   uint8_t /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$ArbiterInfo$userId(void){
#line 88
  unsigned char result;
#line 88

#line 88
  result = /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ArbiterInfo$userId();
#line 88

#line 88
  return result;
#line 88
}
#line 88
# 349 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart0P.nc"
static inline   void HplMsp430Usart0P$Usart$disableRxIntr(void)
#line 349
{
  HplMsp430Usart0P$IE1 &= ~(1 << 6);
}

# 177 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
inline static   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$disableRxIntr(void){
#line 177
  HplMsp430Usart0P$Usart$disableRxIntr();
#line 177
}
#line 177
# 172 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
static inline   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartInterrupts$rxDone(uint8_t data)
#line 172
{

  if (/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_rx_buf) {
    /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_rx_buf[/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_pos - 1] = data;
    }
  if (/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_pos < /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_len) {
    /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$continueOp();
    }
  else 
#line 179
    {
      /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$disableRxIntr();
      /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$signalDone();
    }
}

# 65 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
static inline    void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$Interrupts$default$rxDone(uint8_t id, uint8_t data)
#line 65
{
}

# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
inline static   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$Interrupts$rxDone(uint8_t arg_0x40b38d48, uint8_t arg_0x40a82ae8){
#line 54
  switch (arg_0x40b38d48) {
#line 54
    case /*HplStm25pSpiC.SpiC.UsartC*/Msp430Usart0C$0$CLIENT_ID:
#line 54
      /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartInterrupts$rxDone(arg_0x40a82ae8);
#line 54
      break;
#line 54
    default:
#line 54
      /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$Interrupts$default$rxDone(arg_0x40b38d48, arg_0x40a82ae8);
#line 54
      break;
#line 54
    }
#line 54
}
#line 54
# 80 "/opt/tinyos-2.x/tos/interfaces/ArbiterInfo.nc"
inline static   bool /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$ArbiterInfo$inUse(void){
#line 80
  unsigned char result;
#line 80

#line 80
  result = /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ArbiterInfo$inUse();
#line 80

#line 80
  return result;
#line 80
}
#line 80
# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
static inline   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$RawInterrupts$rxDone(uint8_t data)
#line 54
{
  if (/*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$ArbiterInfo$inUse()) {
    /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$Interrupts$rxDone(/*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$ArbiterInfo$userId(), data);
    }
}

# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
inline static   void HplMsp430Usart0P$Interrupts$rxDone(uint8_t arg_0x40a82ae8){
#line 54
  /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$RawInterrupts$rxDone(arg_0x40a82ae8);
#line 54
}
#line 54
# 55 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430I2C0P.nc"
static inline   bool HplMsp430I2C0P$HplI2C$isI2C(void)
#line 55
{
  /* atomic removed: atomic calls only */
#line 56
  {
    unsigned char __nesc_temp = 
#line 56
    HplMsp430I2C0P$U0CTL & 0x20 && HplMsp430I2C0P$U0CTL & 0x04 && HplMsp430I2C0P$U0CTL & 0x01;

#line 56
    return __nesc_temp;
  }
}

# 6 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430I2C.nc"
inline static   bool HplMsp430Usart0P$HplI2C$isI2C(void){
#line 6
  unsigned char result;
#line 6

#line 6
  result = HplMsp430I2C0P$HplI2C$isI2C();
#line 6

#line 6
  return result;
#line 6
}
#line 6
# 66 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
static inline    void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$I2CInterrupts$default$fired(uint8_t id)
#line 66
{
}

# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430I2CInterrupts.nc"
inline static   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$I2CInterrupts$fired(uint8_t arg_0x40b37468){
#line 39
    /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$I2CInterrupts$default$fired(arg_0x40b37468);
#line 39
}
#line 39
# 59 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
static inline   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$RawI2CInterrupts$fired(void)
#line 59
{
  if (/*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$ArbiterInfo$inUse()) {
    /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$I2CInterrupts$fired(/*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$ArbiterInfo$userId());
    }
}

# 39 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430I2CInterrupts.nc"
inline static   void HplMsp430Usart0P$I2CInterrupts$fired(void){
#line 39
  /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$RawI2CInterrupts$fired();
#line 39
}
#line 39
# 190 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
static inline   void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartInterrupts$txDone(void)
#line 190
{
}

# 64 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
static inline    void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$Interrupts$default$txDone(uint8_t id)
#line 64
{
}

# 49 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
inline static   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$Interrupts$txDone(uint8_t arg_0x40b38d48){
#line 49
  switch (arg_0x40b38d48) {
#line 49
    case /*HplStm25pSpiC.SpiC.UsartC*/Msp430Usart0C$0$CLIENT_ID:
#line 49
      /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$UsartInterrupts$txDone();
#line 49
      break;
#line 49
    default:
#line 49
      /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$Interrupts$default$txDone(arg_0x40b38d48);
#line 49
      break;
#line 49
    }
#line 49
}
#line 49
# 49 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
static inline   void /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$RawInterrupts$txDone(void)
#line 49
{
  if (/*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$ArbiterInfo$inUse()) {
    /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$Interrupts$txDone(/*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$ArbiterInfo$userId());
    }
}

# 49 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
inline static   void HplMsp430Usart0P$Interrupts$txDone(void){
#line 49
  /*Msp430UsartShare0P.UsartShareP*/Msp430UsartShareP$0$RawInterrupts$txDone();
#line 49
}
#line 49
# 88 "/opt/tinyos-2.x/tos/interfaces/ArbiterInfo.nc"
inline static   uint8_t /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$ArbiterInfo$userId(void){
#line 88
  unsigned char result;
#line 88

#line 88
  result = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ArbiterInfo$userId();
#line 88

#line 88
  return result;
#line 88
}
#line 88
# 387 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static inline   void SerialP$SerialFrameComm$dataReceived(uint8_t data)
#line 387
{
  SerialP$rx_state_machine(FALSE, data);
}

# 83 "/opt/tinyos-2.x/tos/lib/serial/SerialFrameComm.nc"
inline static   void HdlcTranslateC$SerialFrameComm$dataReceived(uint8_t arg_0x40c2e0f8){
#line 83
  SerialP$SerialFrameComm$dataReceived(arg_0x40c2e0f8);
#line 83
}
#line 83
# 384 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static inline   void SerialP$SerialFrameComm$delimiterReceived(void)
#line 384
{
  SerialP$rx_state_machine(TRUE, 0);
}

# 74 "/opt/tinyos-2.x/tos/lib/serial/SerialFrameComm.nc"
inline static   void HdlcTranslateC$SerialFrameComm$delimiterReceived(void){
#line 74
  SerialP$SerialFrameComm$delimiterReceived();
#line 74
}
#line 74
# 61 "/opt/tinyos-2.x/tos/lib/serial/HdlcTranslateC.nc"
static inline   void HdlcTranslateC$UartStream$receivedByte(uint8_t data)
#line 61
{






  if (data == HDLC_FLAG_BYTE) {

      HdlcTranslateC$SerialFrameComm$delimiterReceived();
      return;
    }
  else {
#line 73
    if (data == HDLC_CTLESC_BYTE) {

        HdlcTranslateC$state.receiveEscape = 1;
        return;
      }
    else {
#line 78
      if (HdlcTranslateC$state.receiveEscape) {

          HdlcTranslateC$state.receiveEscape = 0;
          data = data ^ 0x20;
        }
      }
    }
#line 83
  HdlcTranslateC$SerialFrameComm$dataReceived(data);
}

# 79 "/opt/tinyos-2.x/tos/interfaces/UartStream.nc"
inline static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$UartStream$receivedByte(uint8_t arg_0x40ccc6f8){
#line 79
  HdlcTranslateC$UartStream$receivedByte(arg_0x40ccc6f8);
#line 79
}
#line 79
# 116 "/opt/tinyos-2.x/tos/lib/serial/HdlcTranslateC.nc"
static inline   void HdlcTranslateC$UartStream$receiveDone(uint8_t *buf, uint16_t len, error_t error)
#line 116
{
}

# 99 "/opt/tinyos-2.x/tos/interfaces/UartStream.nc"
inline static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$UartStream$receiveDone(uint8_t *arg_0x40cca3b8, uint16_t arg_0x40cca540, error_t arg_0x40cca6c0){
#line 99
  HdlcTranslateC$UartStream$receiveDone(arg_0x40cca3b8, arg_0x40cca540, arg_0x40cca6c0);
#line 99
}
#line 99
# 123 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
static inline   void /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartInterrupts$rxDone(uint8_t data)
#line 123
{
  if (/*Msp430Uart1P.UartP*/Msp430UartP$0$m_rx_buf) {
      /*Msp430Uart1P.UartP*/Msp430UartP$0$m_rx_buf[/*Msp430Uart1P.UartP*/Msp430UartP$0$m_rx_pos++] = data;
      if (/*Msp430Uart1P.UartP*/Msp430UartP$0$m_rx_pos >= /*Msp430Uart1P.UartP*/Msp430UartP$0$m_rx_len) {
          uint8_t *buf = /*Msp430Uart1P.UartP*/Msp430UartP$0$m_rx_buf;

#line 128
          /*Msp430Uart1P.UartP*/Msp430UartP$0$m_rx_buf = (void *)0;
          /*Msp430Uart1P.UartP*/Msp430UartP$0$UartStream$receiveDone(buf, /*Msp430Uart1P.UartP*/Msp430UartP$0$m_rx_len, SUCCESS);
        }
    }
  else {
      /*Msp430Uart1P.UartP*/Msp430UartP$0$UartStream$receivedByte(data);
    }
}

# 65 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
static inline    void /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$Interrupts$default$rxDone(uint8_t id, uint8_t data)
#line 65
{
}

# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
inline static   void /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$Interrupts$rxDone(uint8_t arg_0x40b38d48, uint8_t arg_0x40a82ae8){
#line 54
  switch (arg_0x40b38d48) {
#line 54
    case /*PlatformSerialC.UartC.UsartC*/Msp430Usart1C$0$CLIENT_ID:
#line 54
      /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartInterrupts$rxDone(arg_0x40a82ae8);
#line 54
      break;
#line 54
    default:
#line 54
      /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$Interrupts$default$rxDone(arg_0x40b38d48, arg_0x40a82ae8);
#line 54
      break;
#line 54
    }
#line 54
}
#line 54
# 80 "/opt/tinyos-2.x/tos/interfaces/ArbiterInfo.nc"
inline static   bool /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$ArbiterInfo$inUse(void){
#line 80
  unsigned char result;
#line 80

#line 80
  result = /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ArbiterInfo$inUse();
#line 80

#line 80
  return result;
#line 80
}
#line 80
# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
static inline   void /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$RawInterrupts$rxDone(uint8_t data)
#line 54
{
  if (/*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$ArbiterInfo$inUse()) {
    /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$Interrupts$rxDone(/*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$ArbiterInfo$userId(), data);
    }
}

# 54 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
inline static   void HplMsp430Usart1P$Interrupts$rxDone(uint8_t arg_0x40a82ae8){
#line 54
  /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$RawInterrupts$rxDone(arg_0x40a82ae8);
#line 54
}
#line 54
# 391 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static inline bool SerialP$valid_rx_proto(uint8_t proto)
#line 391
{
  switch (proto) {
      case SERIAL_PROTO_PACKET_ACK: 
        return TRUE;
      case SERIAL_PROTO_ACK: 
        case SERIAL_PROTO_PACKET_NOACK: 
          default: 
            return FALSE;
    }
}

# 192 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
static inline void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$lockCurrentBuffer(void)
#line 192
{
  if (/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState.which) {
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState.bufOneLocked = 1;
    }
  else {
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState.bufZeroLocked = 1;
    }
}

#line 188
static inline bool /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$isCurrentBufferLocked(void)
#line 188
{
  return /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState.which ? /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState.bufZeroLocked : /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState.bufOneLocked;
}

#line 215
static inline   error_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$ReceiveBytePacket$startPacket(void)
#line 215
{
  error_t result = SUCCESS;

  /* atomic removed: atomic calls only */
#line 217
  {
    if (!/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$isCurrentBufferLocked()) {


        /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$lockCurrentBuffer();
        /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState.state = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$RECV_STATE_BEGIN;
        /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$recvIndex = 0;
        /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$recvType = TOS_SERIAL_UNKNOWN_ID;
      }
    else {
        result = EBUSY;
      }
  }
  return result;
}

# 51 "/opt/tinyos-2.x/tos/lib/serial/ReceiveBytePacket.nc"
inline static   error_t SerialP$ReceiveBytePacket$startPacket(void){
#line 51
  unsigned char result;
#line 51

#line 51
  result = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$ReceiveBytePacket$startPacket();
#line 51

#line 51
  return result;
#line 51
}
#line 51
# 309 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static __inline uint16_t SerialP$rx_current_crc(void)
#line 309
{
  uint16_t crc;
  uint8_t tmp = SerialP$rxBuf.writePtr;

#line 312
  tmp = tmp == 0 ? SerialP$RX_DATA_BUFFER_SIZE : tmp - 1;
  crc = SerialP$rxBuf.buf[tmp] & 0x00ff;
  crc = (crc << 8) & 0xFF00;
  tmp = tmp == 0 ? SerialP$RX_DATA_BUFFER_SIZE : tmp - 1;
  crc |= SerialP$rxBuf.buf[tmp] & 0x00FF;
  return crc;
}

# 69 "/opt/tinyos-2.x/tos/lib/serial/ReceiveBytePacket.nc"
inline static   void SerialP$ReceiveBytePacket$endPacket(error_t arg_0x40c34d80){
#line 69
  /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$ReceiveBytePacket$endPacket(arg_0x40c34d80);
#line 69
}
#line 69
# 210 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
static inline void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveBufferSwap(void)
#line 210
{
  /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState.which = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState.which ? 0 : 1;
  /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveBuffer = (uint8_t *)/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$messagePtrs[/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState.which];
}

# 56 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
inline static   error_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTask$postTask(void){
#line 56
  unsigned char result;
#line 56

#line 56
  result = SchedulerBasicP$TaskBasic$postTask(/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTask);
#line 56

#line 56
  return result;
#line 56
}
#line 56
# 232 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static __inline bool SerialP$ack_queue_is_full(void)
#line 232
{
  uint8_t tmp;
#line 233
  uint8_t tmp2;

  /* atomic removed: atomic calls only */
#line 234
  {
    tmp = SerialP$ackQ.writePtr;
    tmp2 = SerialP$ackQ.readPtr;
  }
  if (++tmp > SerialP$ACK_QUEUE_SIZE) {
#line 238
    tmp = 0;
    }
#line 239
  return tmp == tmp2;
}







static __inline void SerialP$ack_queue_push(uint8_t token)
#line 248
{
  if (!SerialP$ack_queue_is_full()) {
      /* atomic removed: atomic calls only */
#line 250
      {
        SerialP$ackQ.buf[SerialP$ackQ.writePtr] = token;
        if (++ SerialP$ackQ.writePtr > SerialP$ACK_QUEUE_SIZE) {
#line 252
          SerialP$ackQ.writePtr = 0;
          }
      }
#line 254
      SerialP$MaybeScheduleTx();
    }
}

# 233 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
static inline   void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$ReceiveBytePacket$byteReceived(uint8_t b)
#line 233
{
  /* atomic removed: atomic calls only */
#line 234
  {
    switch (/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState.state) {
        case /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$RECV_STATE_BEGIN: 
          /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState.state = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$RECV_STATE_DATA;
        /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$recvIndex = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$PacketInfo$offset(b);
        /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$recvType = b;
        break;

        case /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$RECV_STATE_DATA: 
          if (/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$recvIndex < sizeof(message_t )) {
              /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveBuffer[/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$recvIndex] = b;
              /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$recvIndex++;
            }
          else {
            }




        break;

        case /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$RECV_STATE_IDLE: 
          default: 
#line 255
            ;
      }
  }
}

# 58 "/opt/tinyos-2.x/tos/lib/serial/ReceiveBytePacket.nc"
inline static   void SerialP$ReceiveBytePacket$byteReceived(uint8_t arg_0x40c347c0){
#line 58
  /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$ReceiveBytePacket$byteReceived(arg_0x40c347c0);
#line 58
}
#line 58
# 299 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static __inline uint8_t SerialP$rx_buffer_top(void)
#line 299
{
  uint8_t tmp = SerialP$rxBuf.buf[SerialP$rxBuf.readPtr];

#line 301
  return tmp;
}

#line 303
static __inline uint8_t SerialP$rx_buffer_pop(void)
#line 303
{
  uint8_t tmp = SerialP$rxBuf.buf[SerialP$rxBuf.readPtr];

#line 305
  if (++ SerialP$rxBuf.readPtr > SerialP$RX_DATA_BUFFER_SIZE) {
#line 305
    SerialP$rxBuf.readPtr = 0;
    }
#line 306
  return tmp;
}

#line 295
static __inline void SerialP$rx_buffer_push(uint8_t data)
#line 295
{
  SerialP$rxBuf.buf[SerialP$rxBuf.writePtr] = data;
  if (++ SerialP$rxBuf.writePtr > SerialP$RX_DATA_BUFFER_SIZE) {
#line 297
    SerialP$rxBuf.writePtr = 0;
    }
}

# 55 "/opt/tinyos-2.x/tos/lib/serial/HdlcTranslateC.nc"
static inline   void HdlcTranslateC$SerialFrameComm$resetReceive(void)
#line 55
{
  HdlcTranslateC$state.receiveEscape = 0;
}

# 68 "/opt/tinyos-2.x/tos/lib/serial/SerialFrameComm.nc"
inline static   void SerialP$SerialFrameComm$resetReceive(void){
#line 68
  HdlcTranslateC$SerialFrameComm$resetReceive();
#line 68
}
#line 68
#line 54
inline static   error_t SerialP$SerialFrameComm$putData(uint8_t arg_0x40c30e60){
#line 54
  unsigned char result;
#line 54

#line 54
  result = HdlcTranslateC$SerialFrameComm$putData(arg_0x40c30e60);
#line 54

#line 54
  return result;
#line 54
}
#line 54
# 513 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static inline   error_t SerialP$SendBytePacket$completeSend(void)
#line 513
{
  bool ret = FAIL;

  /* atomic removed: atomic calls only */
#line 515
  {
    SerialP$txBuf[SerialP$TX_DATA_INDEX].state = SerialP$BUFFER_COMPLETE;
    ret = SUCCESS;
  }
  return ret;
}

# 60 "/opt/tinyos-2.x/tos/lib/serial/SendBytePacket.nc"
inline static   error_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SendBytePacket$completeSend(void){
#line 60
  unsigned char result;
#line 60

#line 60
  result = SerialP$SendBytePacket$completeSend();
#line 60

#line 60
  return result;
#line 60
}
#line 60
# 167 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
static inline   uint8_t /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SendBytePacket$nextByte(void)
#line 167
{
  uint8_t b;
  uint8_t indx;

  /* atomic removed: atomic calls only */
#line 170
  {
    b = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendBuffer[/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendIndex];
    /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendIndex++;
    indx = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendIndex;
  }
  if (indx > /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$sendLen) {
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SendBytePacket$completeSend();
      return 0;
    }
  else {
      return b;
    }
}

# 70 "/opt/tinyos-2.x/tos/lib/serial/SendBytePacket.nc"
inline static   uint8_t SerialP$SendBytePacket$nextByte(void){
#line 70
  unsigned char result;
#line 70

#line 70
  result = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$SendBytePacket$nextByte();
#line 70

#line 70
  return result;
#line 70
}
#line 70
# 642 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static inline   void SerialP$SerialFrameComm$putDone(void)
#line 642
{
  {
    error_t txResult = SUCCESS;

    switch (SerialP$txState) {

        case SerialP$TXSTATE_PROTO: 

          txResult = SerialP$SerialFrameComm$putData(SerialP$txProto);

        SerialP$txState = SerialP$TXSTATE_INFO;



        SerialP$txCRC = crcByte(SerialP$txCRC, SerialP$txProto);
        break;

        case SerialP$TXSTATE_SEQNO: 
          txResult = SerialP$SerialFrameComm$putData(SerialP$txSeqno);
        SerialP$txState = SerialP$TXSTATE_INFO;
        SerialP$txCRC = crcByte(SerialP$txCRC, SerialP$txSeqno);
        break;

        case SerialP$TXSTATE_INFO: /* atomic removed: atomic calls only */
          {
            txResult = SerialP$SerialFrameComm$putData(SerialP$txBuf[SerialP$txIndex].buf);
            SerialP$txCRC = crcByte(SerialP$txCRC, SerialP$txBuf[SerialP$txIndex].buf);
            ++SerialP$txByteCnt;

            if (SerialP$txIndex == SerialP$TX_DATA_INDEX) {
                uint8_t nextByte;

#line 673
                nextByte = SerialP$SendBytePacket$nextByte();
                if (SerialP$txBuf[SerialP$txIndex].state == SerialP$BUFFER_COMPLETE || SerialP$txByteCnt >= SerialP$SERIAL_MTU) {
                    SerialP$txState = SerialP$TXSTATE_FCS1;
                  }
                else {
                    SerialP$txBuf[SerialP$txIndex].buf = nextByte;
                  }
              }
            else {
                SerialP$txState = SerialP$TXSTATE_FCS1;
              }
          }
        break;

        case SerialP$TXSTATE_FCS1: 
          txResult = SerialP$SerialFrameComm$putData(SerialP$txCRC & 0xff);
        SerialP$txState = SerialP$TXSTATE_FCS2;
        break;

        case SerialP$TXSTATE_FCS2: 
          txResult = SerialP$SerialFrameComm$putData((SerialP$txCRC >> 8) & 0xff);
        SerialP$txState = SerialP$TXSTATE_ENDFLAG;
        break;

        case SerialP$TXSTATE_ENDFLAG: 
          txResult = SerialP$SerialFrameComm$putDelimiter();
        SerialP$txState = SerialP$TXSTATE_ENDWAIT;
        break;

        case SerialP$TXSTATE_ENDWAIT: 
          SerialP$txState = SerialP$TXSTATE_FINISH;
        case SerialP$TXSTATE_FINISH: 
          SerialP$MaybeScheduleTx();
        break;
        case SerialP$TXSTATE_ERROR: 
          default: 
            txResult = FAIL;
        break;
      }

    if (txResult != SUCCESS) {
        SerialP$txState = SerialP$TXSTATE_ERROR;
        SerialP$MaybeScheduleTx();
      }
  }
}

# 89 "/opt/tinyos-2.x/tos/lib/serial/SerialFrameComm.nc"
inline static   void HdlcTranslateC$SerialFrameComm$putDone(void){
#line 89
  SerialP$SerialFrameComm$putDone();
#line 89
}
#line 89
# 48 "/opt/tinyos-2.x/tos/interfaces/UartStream.nc"
inline static   error_t HdlcTranslateC$UartStream$send(uint8_t *arg_0x40ccee50, uint16_t arg_0x40ccd010){
#line 48
  unsigned char result;
#line 48

#line 48
  result = /*Msp430Uart1P.UartP*/Msp430UartP$0$UartStream$send(arg_0x40ccee50, arg_0x40ccd010);
#line 48

#line 48
  return result;
#line 48
}
#line 48
# 104 "/opt/tinyos-2.x/tos/lib/serial/HdlcTranslateC.nc"
static inline   void HdlcTranslateC$UartStream$sendDone(uint8_t *buf, uint16_t len, 
error_t error)
#line 105
{
  if (HdlcTranslateC$state.sendEscape) {
      HdlcTranslateC$state.sendEscape = 0;
      HdlcTranslateC$m_data = HdlcTranslateC$txTemp;
      HdlcTranslateC$UartStream$send(&HdlcTranslateC$m_data, 1);
    }
  else {
      HdlcTranslateC$SerialFrameComm$putDone();
    }
}

# 57 "/opt/tinyos-2.x/tos/interfaces/UartStream.nc"
inline static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$UartStream$sendDone(uint8_t *arg_0x40ccd608, uint16_t arg_0x40ccd790, error_t arg_0x40ccd910){
#line 57
  HdlcTranslateC$UartStream$sendDone(arg_0x40ccd608, arg_0x40ccd790, arg_0x40ccd910);
#line 57
}
#line 57
# 384 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart1P.nc"
static inline   void HplMsp430Usart1P$Usart$tx(uint8_t data)
#line 384
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 385
    HplMsp430Usart1P$U1TXBUF = data;
#line 385
    __nesc_atomic_end(__nesc_atomic); }
}

# 224 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart.nc"
inline static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$tx(uint8_t arg_0x40a8fc18){
#line 224
  HplMsp430Usart1P$Usart$tx(arg_0x40a8fc18);
#line 224
}
#line 224
# 149 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
static inline   void /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartInterrupts$txDone(void)
#line 149
{
  if (/*Msp430Uart1P.UartP*/Msp430UartP$0$m_tx_pos < /*Msp430Uart1P.UartP*/Msp430UartP$0$m_tx_len) {
      /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$tx(/*Msp430Uart1P.UartP*/Msp430UartP$0$m_tx_buf[/*Msp430Uart1P.UartP*/Msp430UartP$0$m_tx_pos++]);
    }
  else {
      uint8_t *buf = /*Msp430Uart1P.UartP*/Msp430UartP$0$m_tx_buf;

#line 155
      /*Msp430Uart1P.UartP*/Msp430UartP$0$m_tx_buf = (void *)0;
      /*Msp430Uart1P.UartP*/Msp430UartP$0$UartStream$sendDone(buf, /*Msp430Uart1P.UartP*/Msp430UartP$0$m_tx_len, SUCCESS);
    }
}

# 64 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
static inline    void /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$Interrupts$default$txDone(uint8_t id)
#line 64
{
}

# 49 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
inline static   void /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$Interrupts$txDone(uint8_t arg_0x40b38d48){
#line 49
  switch (arg_0x40b38d48) {
#line 49
    case /*PlatformSerialC.UartC.UsartC*/Msp430Usart1C$0$CLIENT_ID:
#line 49
      /*Msp430Uart1P.UartP*/Msp430UartP$0$UsartInterrupts$txDone();
#line 49
      break;
#line 49
    default:
#line 49
      /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$Interrupts$default$txDone(arg_0x40b38d48);
#line 49
      break;
#line 49
    }
#line 49
}
#line 49
# 49 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UsartShareP.nc"
static inline   void /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$RawInterrupts$txDone(void)
#line 49
{
  if (/*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$ArbiterInfo$inUse()) {
    /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$Interrupts$txDone(/*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$ArbiterInfo$userId());
    }
}

# 49 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430UsartInterrupts.nc"
inline static   void HplMsp430Usart1P$Interrupts$txDone(void){
#line 49
  /*Msp430UsartShare1P.UsartShareP*/Msp430UsartShareP$1$RawInterrupts$txDone();
#line 49
}
#line 49
# 210 "/opt/tinyos-2.x/tos/chips/msp430/msp430hardware.h"
 __nesc_atomic_t __nesc_atomic_start(void )
{
  __nesc_atomic_t result = (({
#line 212
    uint16_t __x;

#line 212
     __asm volatile ("mov	r2, %0" : "=r"((uint16_t )__x));__x;
  }
  )
#line 212
   & 0x0008) != 0;

#line 213
  __nesc_disable_interrupt();
   __asm volatile ("" :  :  : "memory");
  return result;
}

 void __nesc_atomic_end(__nesc_atomic_t reenable_interrupts)
{
   __asm volatile ("" :  :  : "memory");
  if (reenable_interrupts) {
    __nesc_enable_interrupt();
    }
}

# 11 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCommonP.nc"
__attribute((wakeup)) __attribute((interrupt(12)))  void sig_TIMERA0_VECTOR(void)
#line 11
{
#line 11
  Msp430TimerCommonP$VectorTimerA0$fired();
}

# 169 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCapComP.nc"
static   void /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Event$fired(void)
{
  if (/*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Control$getControl().cap) {
    /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Capture$captured(/*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Capture$getEvent());
    }
  else {
#line 174
    /*Msp430TimerC.Msp430TimerA0*/Msp430TimerCapComP$0$Compare$fired();
    }
}

#line 169
static   void /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Event$fired(void)
{
  if (/*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Control$getControl().cap) {
    /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Capture$captured(/*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Capture$getEvent());
    }
  else {
#line 174
    /*Msp430TimerC.Msp430TimerA1*/Msp430TimerCapComP$1$Compare$fired();
    }
}

#line 169
static   void /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Event$fired(void)
{
  if (/*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Control$getControl().cap) {
    /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Capture$captured(/*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Capture$getEvent());
    }
  else {
#line 174
    /*Msp430TimerC.Msp430TimerA2*/Msp430TimerCapComP$2$Compare$fired();
    }
}

# 12 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCommonP.nc"
__attribute((wakeup)) __attribute((interrupt(10)))  void sig_TIMERA1_VECTOR(void)
#line 12
{
#line 12
  Msp430TimerCommonP$VectorTimerA1$fired();
}

#line 13
__attribute((wakeup)) __attribute((interrupt(26)))  void sig_TIMERB0_VECTOR(void)
#line 13
{
#line 13
  Msp430TimerCommonP$VectorTimerB0$fired();
}

# 135 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerP.nc"
static    void /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Event$default$fired(uint8_t n)
{
}

# 28 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerEvent.nc"
static   void /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Event$fired(uint8_t arg_0x4056f4f0){
#line 28
  switch (arg_0x4056f4f0) {
#line 28
    case 0:
#line 28
      /*Msp430TimerC.Msp430TimerB0*/Msp430TimerCapComP$3$Event$fired();
#line 28
      break;
#line 28
    case 1:
#line 28
      /*Msp430TimerC.Msp430TimerB1*/Msp430TimerCapComP$4$Event$fired();
#line 28
      break;
#line 28
    case 2:
#line 28
      /*Msp430TimerC.Msp430TimerB2*/Msp430TimerCapComP$5$Event$fired();
#line 28
      break;
#line 28
    case 3:
#line 28
      /*Msp430TimerC.Msp430TimerB3*/Msp430TimerCapComP$6$Event$fired();
#line 28
      break;
#line 28
    case 4:
#line 28
      /*Msp430TimerC.Msp430TimerB4*/Msp430TimerCapComP$7$Event$fired();
#line 28
      break;
#line 28
    case 5:
#line 28
      /*Msp430TimerC.Msp430TimerB5*/Msp430TimerCapComP$8$Event$fired();
#line 28
      break;
#line 28
    case 6:
#line 28
      /*Msp430TimerC.Msp430TimerB6*/Msp430TimerCapComP$9$Event$fired();
#line 28
      break;
#line 28
    case 7:
#line 28
      /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Overflow$fired();
#line 28
      break;
#line 28
    default:
#line 28
      /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Event$default$fired(arg_0x4056f4f0);
#line 28
      break;
#line 28
    }
#line 28
}
#line 28
# 159 "/opt/tinyos-2.x/tos/system/SchedulerBasicP.nc"
static   error_t SchedulerBasicP$TaskBasic$postTask(uint8_t id)
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 161
    {
#line 161
      {
        unsigned char __nesc_temp = 
#line 161
        SchedulerBasicP$pushTask(id) ? SUCCESS : EBUSY;

        {
#line 161
          __nesc_atomic_end(__nesc_atomic); 
#line 161
          return __nesc_temp;
        }
      }
    }
#line 164
    __nesc_atomic_end(__nesc_atomic); }
}

# 96 "/opt/tinyos-2.x/tos/lib/timer/TransformAlarmC.nc"
static void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$set_alarm(void)
{
  /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type now = /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Counter$get();
#line 98
  /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type expires;
#line 98
  /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type remaining;




  expires = /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_t0 + /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_dt;


  remaining = (/*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type )(expires - now);


  if (/*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_t0 <= now) 
    {
      if (expires >= /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_t0 && 
      expires <= now) {
        remaining = 0;
        }
    }
  else {
      if (expires >= /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_t0 || 
      expires <= now) {
        remaining = 0;
        }
    }
#line 121
  if (remaining > /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$MAX_DELAY) 
    {
      /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_t0 = now + /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$MAX_DELAY;
      /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_dt = remaining - /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$MAX_DELAY;
      remaining = /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$MAX_DELAY;
    }
  else 
    {
      /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_t0 += /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_dt;
      /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_dt = 0;
    }
  /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$AlarmFrom$startAt((/*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$from_size_type )now << 5, 
  (/*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$from_size_type )remaining << 5);
}

# 69 "/opt/tinyos-2.x/tos/lib/timer/TransformCounterC.nc"
static   /*CounterMilli32C.Transform*/TransformCounterC$0$to_size_type /*CounterMilli32C.Transform*/TransformCounterC$0$Counter$get(void)
{
  /*CounterMilli32C.Transform*/TransformCounterC$0$to_size_type rv = 0;

#line 72
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
    {
      /*CounterMilli32C.Transform*/TransformCounterC$0$upper_count_type high = /*CounterMilli32C.Transform*/TransformCounterC$0$m_upper;
      /*CounterMilli32C.Transform*/TransformCounterC$0$from_size_type low = /*CounterMilli32C.Transform*/TransformCounterC$0$CounterFrom$get();

#line 76
      if (/*CounterMilli32C.Transform*/TransformCounterC$0$CounterFrom$isOverflowPending()) 
        {






          high++;
          low = /*CounterMilli32C.Transform*/TransformCounterC$0$CounterFrom$get();
        }
      {
        /*CounterMilli32C.Transform*/TransformCounterC$0$to_size_type high_to = high;
        /*CounterMilli32C.Transform*/TransformCounterC$0$to_size_type low_to = low >> /*CounterMilli32C.Transform*/TransformCounterC$0$LOW_SHIFT_RIGHT;

#line 90
        rv = (high_to << /*CounterMilli32C.Transform*/TransformCounterC$0$HIGH_SHIFT_LEFT) | low_to;
      }
    }
#line 92
    __nesc_atomic_end(__nesc_atomic); }
  return rv;
}

# 51 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerP.nc"
static   uint16_t /*Msp430TimerC.Msp430TimerB*/Msp430TimerP$1$Timer$get(void)
{




  if (1) {
      /* atomic removed: atomic calls only */
#line 58
      {
        uint16_t t0;
        uint16_t t1 = * (volatile uint16_t *)400U;

#line 61
        do {
#line 61
            t0 = t1;
#line 61
            t1 = * (volatile uint16_t *)400U;
          }
        while (
#line 61
        t0 != t1);
        {
          unsigned int __nesc_temp = 
#line 62
          t1;

#line 62
          return __nesc_temp;
        }
      }
    }
  else 
#line 65
    {
      return * (volatile uint16_t *)400U;
    }
}

# 14 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430TimerCommonP.nc"
__attribute((wakeup)) __attribute((interrupt(24)))  void sig_TIMERB1_VECTOR(void)
#line 14
{
#line 14
  Msp430TimerCommonP$VectorTimerB1$fired();
}

# 52 "/opt/tinyos-2.x/tos/system/RealMainP.nc"
  int main(void)
#line 52
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
    {





      {
      }
#line 60
      ;

      RealMainP$Scheduler$init();





      RealMainP$PlatformInit$init();
      while (RealMainP$Scheduler$runNextTask()) ;





      RealMainP$SoftwareInit$init();
      while (RealMainP$Scheduler$runNextTask()) ;
    }
#line 77
    __nesc_atomic_end(__nesc_atomic); }


  __nesc_enable_interrupt();

  RealMainP$Boot$booted();


  RealMainP$Scheduler$taskLoop();




  return -1;
}

# 160 "/opt/tinyos-2.x/tos/chips/msp430/timer/Msp430ClockP.nc"
static void Msp430ClockP$set_dco_calib(int calib)
{
  BCSCTL1 = (BCSCTL1 & ~0x07) | ((calib >> 8) & 0x07);
  DCOCTL = calib & 0xff;
}

# 16 "/opt/tinyos-2.x/tos/platforms/telosb/MotePlatformC.nc"
static void MotePlatformC$TOSH_FLASH_M25P_DP_bit(bool set)
#line 16
{
  if (set) {
    TOSH_SET_SIMO0_PIN();
    }
  else {
#line 20
    TOSH_CLR_SIMO0_PIN();
    }
#line 21
  TOSH_SET_UCLK0_PIN();
  TOSH_CLR_UCLK0_PIN();
}

# 123 "/opt/tinyos-2.x/tos/system/SchedulerBasicP.nc"
static  bool SchedulerBasicP$Scheduler$runNextTask(void)
{
  uint8_t nextTask;

  /* atomic removed: atomic calls only */
#line 127
  {
    nextTask = SchedulerBasicP$popTask();
    if (nextTask == SchedulerBasicP$NO_TASK) 
      {
        {
          unsigned char __nesc_temp = 
#line 131
          FALSE;

#line 131
          return __nesc_temp;
        }
      }
  }
#line 134
  SchedulerBasicP$TaskBasic$runTask(nextTask);
  return TRUE;
}

#line 164
static   void SchedulerBasicP$TaskBasic$default$runTask(uint8_t id)
{
}

# 64 "/opt/tinyos-2.x/tos/interfaces/TaskBasic.nc"
static  void SchedulerBasicP$TaskBasic$runTask(uint8_t arg_0x404aa1e0){
#line 64
  switch (arg_0x404aa1e0) {
#line 64
    case Stm25pSectorP$signalDone_task:
#line 64
      Stm25pSectorP$signalDone_task$runTask();
#line 64
      break;
#line 64
    case /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$grantedTask:
#line 64
      /*Stm25pSectorC.ArbiterC.Arbiter*/ArbiterP$0$grantedTask$runTask();
#line 64
      break;
#line 64
    case /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$fired:
#line 64
      /*HilTimerMilliC.AlarmToTimerC*/AlarmToTimerC$0$fired$runTask();
#line 64
      break;
#line 64
    case /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$updateFromTimer:
#line 64
      /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$updateFromTimer$runTask();
#line 64
      break;
#line 64
    case /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$startTask:
#line 64
      /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$startTask$runTask();
#line 64
      break;
#line 64
    case /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$timerTask:
#line 64
      /*Stm25pSectorC.PowerManagerC.PowerManager*/DeferredPowerManagerP$0$timerTask$runTask();
#line 64
      break;
#line 64
    case /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$signalDone_task:
#line 64
      /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$signalDone_task$runTask();
#line 64
      break;
#line 64
    case /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$grantedTask:
#line 64
      /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$grantedTask$runTask();
#line 64
      break;
#line 64
    case SerialP$RunTx:
#line 64
      SerialP$RunTx$runTask();
#line 64
      break;
#line 64
    case SerialP$startDoneTask:
#line 64
      SerialP$startDoneTask$runTask();
#line 64
      break;
#line 64
    case SerialP$stopDoneTask:
#line 64
      SerialP$stopDoneTask$runTask();
#line 64
      break;
#line 64
    case SerialP$defaultSerialFlushTask:
#line 64
      SerialP$defaultSerialFlushTask$runTask();
#line 64
      break;
#line 64
    case /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$signalSendDone:
#line 64
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$signalSendDone$runTask();
#line 64
      break;
#line 64
    case /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTask:
#line 64
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTask$runTask();
#line 64
      break;
#line 64
    case /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$grantedTask:
#line 64
      /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$grantedTask$runTask();
#line 64
      break;
#line 64
    case /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$CancelTask:
#line 64
      /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$CancelTask$runTask();
#line 64
      break;
#line 64
    case /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$errorTask:
#line 64
      /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$errorTask$runTask();
#line 64
      break;
#line 64
    default:
#line 64
      SchedulerBasicP$TaskBasic$default$runTask(arg_0x404aa1e0);
#line 64
      break;
#line 64
    }
#line 64
}
#line 64
# 47 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static   void /*HplMsp430GeneralIOC.P55*/HplMsp430GeneralIOP$37$IO$toggle(void)
#line 47
{
#line 47
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 47
    * (volatile uint8_t *)49U ^= 0x01 << 5;
#line 47
    __nesc_atomic_end(__nesc_atomic); }
}

# 112 "TraceRetrieverP.nc"
static  void TraceRetrieverP$UARTSendData$sendDone(message_t *bufPtr, error_t error)
#line 112
{
  TraceRetrieverP$logIndex = TraceRetrieverP$logIndex + 1;

  if (TraceRetrieverP$logIndex < ENTRIES_PER_LOG) {
      TraceRetrieverP$sendLogData();
    }
  else 
#line 117
    {
      TraceRetrieverP$readNextLog();
    }
}

# 156 "/opt/tinyos-2.x/tos/lib/serial/SerialActiveMessageP.nc"
static  void /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$setType(message_t *amsg, am_id_t type)
#line 156
{
  serial_header_t *header = /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$getHeader(amsg);

#line 158
  __nesc_hton_uint8((unsigned char *)&header->type, type);
}

# 82 "/opt/tinyos-2.x/tos/system/AMQueueImplP.nc"
static  error_t /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Send$send(uint8_t clientId, message_t *msg, 
uint8_t len)
#line 83
{
  if (clientId >= 2) {
      return FAIL;
    }
  if (/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$queue[clientId].msg != (void *)0) {
      return EBUSY;
    }
  ;

  /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$queue[clientId].msg = msg;
  /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Packet$setPayloadLength(msg, len);

  if (/*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current >= 2) {
      error_t err;
      am_id_t amId = /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$AMPacket$type(msg);
      am_addr_t dest = /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$AMPacket$destination(msg);

      ;
      /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current = clientId;

      err = /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$AMSend$send(amId, dest, msg, len);
      if (err != SUCCESS) {
          ;
          /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$current = 2;
          /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$queue[clientId].msg = (void *)0;
        }

      return err;
    }
  else {
      ;
    }
  return SUCCESS;
}

# 151 "/opt/tinyos-2.x/tos/lib/serial/SerialActiveMessageP.nc"
static  am_id_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$type(message_t *amsg)
#line 151
{
  serial_header_t *header = /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$getHeader(amsg);

#line 153
  return __nesc_ntoh_uint8((unsigned char *)&header->type);
}

#line 127
static  am_addr_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMPacket$destination(message_t *amsg)
#line 127
{
  serial_header_t *header = /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$getHeader(amsg);

#line 129
  return __nesc_ntoh_uint16((unsigned char *)&header->dest);
}

#line 53
static  error_t /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$AMSend$send(am_id_t id, am_addr_t dest, 
message_t *msg, 
uint8_t len)
#line 55
{
  serial_header_t *header = /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$getHeader(msg);

#line 57
  __nesc_hton_uint16((unsigned char *)&header->dest, dest);





  __nesc_hton_uint8((unsigned char *)&header->type, id);
  __nesc_hton_uint8((unsigned char *)&header->length, len);

  return /*SerialActiveMessageC.AM*/SerialActiveMessageP$0$SubSend$send(msg, len);
}

# 502 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static void SerialP$MaybeScheduleTx(void)
#line 502
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 503
    {
      if (SerialP$txPending == 0) {
          if (SerialP$RunTx$postTask() == SUCCESS) {
              SerialP$txPending = 1;
            }
        }
    }
#line 509
    __nesc_atomic_end(__nesc_atomic); }
}

# 46 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static   void /*HplMsp430GeneralIOC.P54*/HplMsp430GeneralIOP$36$IO$clr(void)
#line 46
{
#line 46
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 46
    * (volatile uint8_t *)49U &= ~(0x01 << 4);
#line 46
    __nesc_atomic_end(__nesc_atomic); }
}

# 155 "/opt/tinyos-2.x/tos/system/AMQueueImplP.nc"
static void /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$sendDone(uint8_t last, message_t *msg, error_t err)
#line 155
{
  /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$queue[last].msg = (void *)0;
  /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$tryToSend();
  /*SerialAMQueueP.AMQueueImplP*/AMQueueImplP$0$Send$sendDone(last, msg, err);
}

# 82 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
static   void /*Msp430Uart1P.UartP*/Msp430UartP$0$ResourceConfigure$configure(uint8_t id)
#line 82
{
  msp430_uart_union_config_t *config = /*Msp430Uart1P.UartP*/Msp430UartP$0$Msp430UartConfigure$getConfig(id);

#line 84
  /*Msp430Uart1P.UartP*/Msp430UartP$0$m_byte_time = config->uartConfig.ubr / 2;
  /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$setModeUart(config);
  /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$enableIntr();
}

# 251 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart1P.nc"
static   void HplMsp430Usart1P$Usart$disableSpi(void)
#line 251
{
  /* atomic removed: atomic calls only */
#line 252
  {
    HplMsp430Usart1P$ME2 &= ~(1 << 4);
    HplMsp430Usart1P$SIMO$selectIOFunc();
    HplMsp430Usart1P$SOMI$selectIOFunc();
    HplMsp430Usart1P$UCLK$selectIOFunc();
  }
}

#line 211
static   void HplMsp430Usart1P$Usart$disableUart(void)
#line 211
{
  /* atomic removed: atomic calls only */
#line 212
  {
    HplMsp430Usart1P$ME2 &= ~((1 << 5) | (1 << 4));
    HplMsp430Usart1P$UTXD$selectIOFunc();
    HplMsp430Usart1P$URXD$selectIOFunc();
  }
}

# 347 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static void SerialP$testOff(void)
#line 347
{
  bool turnOff = FALSE;

#line 349
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 349
    {
      if (SerialP$txState == SerialP$TXSTATE_INACTIVE && 
      SerialP$rxState == SerialP$RXSTATE_INACTIVE) {
          turnOff = TRUE;
        }
    }
#line 354
    __nesc_atomic_end(__nesc_atomic); }
  if (turnOff) {
      SerialP$stopDoneTask$postTask();
      { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 357
        SerialP$offPending = FALSE;
#line 357
        __nesc_atomic_end(__nesc_atomic); }
    }
  else {
      { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 360
        SerialP$offPending = TRUE;
#line 360
        __nesc_atomic_end(__nesc_atomic); }
    }
}

# 86 "/opt/tinyos-2.x/tos/lib/serial/HdlcTranslateC.nc"
static   error_t HdlcTranslateC$SerialFrameComm$putDelimiter(void)
#line 86
{
  HdlcTranslateC$state.sendEscape = 0;
  HdlcTranslateC$m_data = HDLC_FLAG_BYTE;
  return HdlcTranslateC$UartStream$send(&HdlcTranslateC$m_data, 1);
}

# 137 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430UartP.nc"
static   error_t /*Msp430Uart1P.UartP*/Msp430UartP$0$UartStream$send(uint8_t *buf, uint16_t len)
#line 137
{
  if (len == 0) {
    return FAIL;
    }
  else {
#line 140
    if (/*Msp430Uart1P.UartP*/Msp430UartP$0$m_tx_buf) {
      return EBUSY;
      }
    }
#line 142
  /*Msp430Uart1P.UartP*/Msp430UartP$0$m_tx_buf = buf;
  /*Msp430Uart1P.UartP*/Msp430UartP$0$m_tx_len = len;
  /*Msp430Uart1P.UartP*/Msp430UartP$0$m_tx_pos = 0;
  /*Msp430Uart1P.UartP*/Msp430UartP$0$Usart$tx(buf[/*Msp430Uart1P.UartP*/Msp430UartP$0$m_tx_pos++]);
  return SUCCESS;
}

# 86 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpiP.nc"
static uint8_t Stm25pSpiP$sendCmd(uint8_t cmd, uint8_t len)
#line 86
{

  uint8_t tmp = 0;
  int i;

  Stm25pSpiP$CSN$clr();
  for (i = 0; i < len; i++) 
    tmp = Stm25pSpiP$SpiByte$write(cmd);
  Stm25pSpiP$CSN$set();

  return tmp;
}

# 46 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static   void /*HplMsp430GeneralIOC.P44*/HplMsp430GeneralIOP$28$IO$clr(void)
#line 46
{
#line 46
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 46
    * (volatile uint8_t *)29U &= ~(0x01 << 4);
#line 46
    __nesc_atomic_end(__nesc_atomic); }
}

# 386 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart0P.nc"
static   uint8_t HplMsp430Usart0P$Usart$rx(void)
#line 386
{
  uint8_t value;

#line 388
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 388
    value = U0RXBUF;
#line 388
    __nesc_atomic_end(__nesc_atomic); }
  return value;
}

# 45 "/opt/tinyos-2.x/tos/chips/msp430/pins/HplMsp430GeneralIOP.nc"
static   void /*HplMsp430GeneralIOC.P44*/HplMsp430GeneralIOP$28$IO$set(void)
#line 45
{
#line 45
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 45
    * (volatile uint8_t *)29U |= 0x01 << 4;
#line 45
    __nesc_atomic_end(__nesc_atomic); }
}

# 108 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static   error_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Resource$release(uint8_t id)
#line 108
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 109
    {
      if (/*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$state == /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$RES_BUSY && /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$resId == id) {
          if (/*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Queue$isEmpty() == FALSE) {
              /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$reqResId = /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Queue$dequeue();
              /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$state = /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$RES_GRANTING;
              /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$grantedTask$postTask();
            }
          else {
              /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$resId = /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$default_owner_id;
              /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$state = /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$RES_CONTROLLED;
              /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$granted();
            }
          /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceConfigure$unconfigure(id);
        }
    }
#line 123
    __nesc_atomic_end(__nesc_atomic); }
  return FAIL;
}

# 88 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static  error_t Stm25pSectorP$SplitControl$start(void)
#line 88
{
  error_t error = Stm25pSectorP$SpiResource$request();

#line 90
  if (error == SUCCESS) {
    Stm25pSectorP$m_power_state = Stm25pSectorP$S_START;
    }
#line 92
  return error;
}

# 77 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static   error_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Resource$request(uint8_t id)
#line 77
{
  /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceRequested$requested(/*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$resId);
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 79
    {
      if (/*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$state == /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$RES_CONTROLLED) {
          /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$state = /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$RES_GRANTING;
          /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$reqResId = id;
        }
      else {
          unsigned char __nesc_temp = 
#line 84
          /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$Queue$enqueue(id);

          {
#line 84
            __nesc_atomic_end(__nesc_atomic); 
#line 84
            return __nesc_temp;
          }
        }
    }
#line 87
    __nesc_atomic_end(__nesc_atomic); }
#line 86
  /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ResourceDefaultOwner$requested();
  return SUCCESS;
}

# 214 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static  void Stm25pLogP$ClientResource$granted(uint8_t id)
#line 214
{


  if (Stm25pLogP$m_log_info[id].read_addr == STM25P_INVALID_ADDRESS && 
  Stm25pLogP$m_log_state[id].req != Stm25pLogP$S_ERASE) {
      Stm25pLogP$m_rw_state = Stm25pLogP$S_SEARCH_BLOCKS;
      Stm25pLogP$Sector$read(id, 0, (uint8_t *)&Stm25pLogP$m_addr, sizeof Stm25pLogP$m_addr);
    }
  else 
    {
      switch (Stm25pLogP$m_log_state[id].req) {
          case Stm25pLogP$S_READ: 
            Stm25pLogP$m_rw_state = Stm25pLogP$m_log_info[id].remaining ? Stm25pLogP$S_DATA : Stm25pLogP$S_HEADER;
          Stm25pLogP$continueReadOp(id);
          break;
          case Stm25pLogP$S_SEEK: 
            {

              uint8_t numSectors = Stm25pLogP$Sector$getNumSectors(id);
              uint8_t readSector = 
              Stm25pLogP$m_log_state[id].cookie >> STM25P_SECTOR_SIZE_LOG2;
              uint8_t writeSector = ((
              Stm25pLogP$m_log_info[id].write_addr - 1) >> STM25P_SECTOR_SIZE_LOG2) + 1;

              if (writeSector - readSector > numSectors) {
                  Stm25pLogP$m_log_state[id].cookie = 
                  (storage_cookie_t )(writeSector - numSectors)
                   << STM25P_SECTOR_SIZE_LOG2;
                }
              Stm25pLogP$m_log_info[id].read_addr = Stm25pLogP$m_log_state[id].cookie & ~Stm25pLogP$BLOCK_MASK;
              Stm25pLogP$m_log_info[id].remaining = 0;
              Stm25pLogP$m_rw_state = Stm25pLogP$S_SEARCH_SEEK;
              if (Stm25pLogP$m_log_info[id].read_addr != Stm25pLogP$m_log_state[id].cookie) {
                Stm25pLogP$Sector$read(id, Stm25pLogP$calcAddr(id, Stm25pLogP$m_log_info[id].read_addr), 
                &Stm25pLogP$m_header, sizeof Stm25pLogP$m_header);
                }
              else {
#line 250
                Stm25pLogP$signalDone(id, SUCCESS);
                }
            }
#line 252
          break;
          case Stm25pLogP$S_ERASE: 
            Stm25pLogP$Sector$erase(id, 0, Stm25pLogP$Sector$getNumSectors(id));
          break;
          case Stm25pLogP$S_APPEND: 
            Stm25pLogP$m_rw_state = Stm25pLogP$S_HEADER;
          Stm25pLogP$continueAppendOp(id);
          break;
          case Stm25pLogP$S_SYNC: 
            Stm25pLogP$signalDone(id, SUCCESS);
          break;
          case Stm25pLogP$S_IDLE: 
            break;
        }
    }
}

# 171 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static  error_t Stm25pSectorP$Sector$read(uint8_t id, stm25p_addr_t addr, uint8_t *buf, 
stm25p_len_t len)
#line 172
{

  Stm25pSectorP$m_state = Stm25pSectorP$S_READ;
  Stm25pSectorP$m_addr = addr;
  Stm25pSectorP$m_buf = buf;
  Stm25pSectorP$m_len = len;

  return Stm25pSectorP$Spi$read(Stm25pSectorP$physicalAddr(id, addr), buf, len);
}

# 176 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpiP.nc"
static error_t Stm25pSpiP$newRequest(bool write, stm25p_len_t cmd_len)
#line 176
{
  Stm25pSpiP$m_cmd[1] = Stm25pSpiP$m_addr >> 16;
  Stm25pSpiP$m_cmd[2] = Stm25pSpiP$m_addr >> 8;
  Stm25pSpiP$m_cmd[3] = Stm25pSpiP$m_addr;
  if (write) {
    Stm25pSpiP$sendCmd(Stm25pSpiP$S_WRITE_ENABLE, 1);
    }
#line 182
  Stm25pSpiP$CSN$clr();
  Stm25pSpiP$SpiPacket$send(Stm25pSpiP$m_cmd, (void *)0, cmd_len);
  return SUCCESS;
}

# 146 "/opt/tinyos-2.x/tos/chips/msp430/usart/Msp430SpiNoDmaP.nc"
static   error_t /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$SpiPacket$send(uint8_t id, uint8_t *tx_buf, 
uint8_t *rx_buf, 
uint16_t len)
#line 148
{

  /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_client = id;
  /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_tx_buf = tx_buf;
  /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_rx_buf = rx_buf;
  /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_len = len;
  /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_pos = 0;

  if (len) {
      /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$enableRxIntr();
      /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$continueOp();
    }
  else {
      /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$signalDone_task$postTask();
    }

  return SUCCESS;
}

#line 120
static void /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$continueOp(void)
#line 120
{

  uint8_t end;
  uint8_t tmp;

  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
#line 125
    {
      /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$tx(/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_tx_buf ? /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_tx_buf[/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_pos] : 0);

      end = /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_pos + /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$SPI_ATOMIC_SIZE;
      if (end > /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_len) {
        end = /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_len;
        }
      while (++/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_pos < end) {
          while (!/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$isTxIntrPending()) ;
          /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$clrTxIntr();
          /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$tx(/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_tx_buf ? /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_tx_buf[/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_pos] : 0);
          while (!/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$isRxIntrPending()) ;
          /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$clrRxIntr();
          tmp = /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$Usart$rx();
          if (/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_rx_buf) {
            /*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_rx_buf[/*Msp430SpiNoDma0P.SpiP*/Msp430SpiNoDmaP$0$m_pos - 1] = tmp;
            }
        }
    }
#line 143
    __nesc_atomic_end(__nesc_atomic); }
}

# 270 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static void Stm25pLogP$continueReadOp(uint8_t client)
#line 270
{

  stm25p_addr_t read_addr = Stm25pLogP$m_log_info[client].read_addr;
  uint8_t *buf;
  uint8_t len;


  if (Stm25pLogP$m_len == 0 || read_addr >= Stm25pLogP$m_log_info[client].write_addr) {
      Stm25pLogP$signalDone(client, SUCCESS);
      return;
    }

  buf = &Stm25pLogP$m_header;
  len = sizeof Stm25pLogP$m_header;

  if (Stm25pLogP$m_rw_state == Stm25pLogP$S_DATA) {

      if (Stm25pLogP$m_header == Stm25pLogP$INVALID_HEADER) {
          Stm25pLogP$m_rw_state = Stm25pLogP$S_HEADER;
          read_addr += Stm25pLogP$BLOCK_SIZE;
          read_addr &= ~Stm25pLogP$BLOCK_MASK;
        }
      else {
          buf = Stm25pLogP$m_log_state[client].buf + Stm25pLogP$m_log_state[client].len - Stm25pLogP$m_len;

          if (Stm25pLogP$m_log_info[client].remaining < Stm25pLogP$m_len) {
            len = Stm25pLogP$m_log_info[client].remaining;
            }
          else {
#line 298
            len = Stm25pLogP$m_len;
            }
        }
    }

  if (!((uint16_t )read_addr & Stm25pLogP$BLOCK_MASK)) {
    read_addr += sizeof Stm25pLogP$m_addr;
    }
  Stm25pLogP$m_log_info[client].read_addr = read_addr;
  Stm25pLogP$Sector$read(client, Stm25pLogP$calcAddr(client, read_addr), buf, len);
}

#line 483
static void Stm25pLogP$signalDone(uint8_t id, error_t error)
#line 483
{

  Stm25pLogP$stm25p_log_req_t req = Stm25pLogP$m_log_state[id].req;
  void *buf = Stm25pLogP$m_log_state[id].buf;
  storage_len_t len = Stm25pLogP$m_log_state[id].len;

  Stm25pLogP$ClientResource$release(id);
  Stm25pLogP$m_log_state[id].req = Stm25pLogP$S_IDLE;
  switch (req) {
      case Stm25pLogP$S_IDLE: 
        break;
      case Stm25pLogP$S_READ: 
        Stm25pLogP$Read$readDone(id, buf, len - Stm25pLogP$m_len, error);
      break;
      case Stm25pLogP$S_SEEK: 
        Stm25pLogP$Read$seekDone(id, error);
      break;
      case Stm25pLogP$S_ERASE: 
        Stm25pLogP$Write$eraseDone(id, error);
      break;
      case Stm25pLogP$S_APPEND: 
        Stm25pLogP$Write$appendDone(id, buf, len, Stm25pLogP$m_records_lost, error);
      break;
      case Stm25pLogP$S_SYNC: 
        Stm25pLogP$Write$syncDone(id, error);
      break;
    }
}

#line 207
static stm25p_addr_t Stm25pLogP$calcAddr(uint8_t client, stm25p_addr_t addr)
#line 207
{
  stm25p_addr_t result = Stm25pLogP$calcSector(client, addr);

#line 209
  result <<= STM25P_SECTOR_SIZE_LOG2;
  result |= addr & STM25P_SECTOR_MASK;
  return result;
}

# 167 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static  uint8_t Stm25pSectorP$Sector$getNumSectors(uint8_t id)
#line 167
{
  return STM25P_VMAP[Stm25pSectorP$getVolumeId(id)].size;
}

#line 213
static  error_t Stm25pSectorP$Sector$erase(uint8_t id, uint8_t sector, 
uint8_t num_sectors)
#line 214
{

  Stm25pSectorP$m_state = Stm25pSectorP$S_ERASE;
  Stm25pSectorP$m_addr = sector;
  Stm25pSectorP$m_len = num_sectors;
  Stm25pSectorP$m_cur_len = 0;

  return Stm25pSectorP$Spi$sectorErase(STM25P_VMAP[Stm25pSectorP$getVolumeId(id)].base + Stm25pSectorP$m_addr + 
  Stm25pSectorP$m_cur_len);
}

# 165 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpiP.nc"
static   error_t Stm25pSpiP$Spi$sectorErase(uint8_t sector)
#line 165
{
  Stm25pSpiP$m_cmd[0] = Stm25pSpiP$S_SECTOR_ERASE;
  Stm25pSpiP$m_addr = (stm25p_addr_t )sector << STM25P_SECTOR_SIZE_LOG2;
  return Stm25pSpiP$newRequest(TRUE, 4);
}

# 416 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pLogP.nc"
static void Stm25pLogP$continueAppendOp(uint8_t client)
#line 416
{

  stm25p_addr_t write_addr = Stm25pLogP$m_log_info[client].write_addr;
  void *buf;
  uint8_t len;

  if (! (uint16_t )write_addr) {
      Stm25pLogP$m_records_lost = TRUE;
      Stm25pLogP$Sector$erase(client, Stm25pLogP$calcSector(client, write_addr), 1);
    }
  else {
      if (!((uint16_t )write_addr & Stm25pLogP$BLOCK_MASK)) {
          buf = & Stm25pLogP$m_log_info[client].write_addr;
          len = sizeof Stm25pLogP$m_addr;
        }
      else {
#line 431
        if (Stm25pLogP$m_rw_state == Stm25pLogP$S_HEADER) {
            buf = & Stm25pLogP$m_log_state[client].len;
            len = sizeof  Stm25pLogP$m_log_state[client].len;
          }
        else {
            buf = Stm25pLogP$m_log_state[client].buf;
            len = Stm25pLogP$m_log_state[client].len;
          }
        }
#line 439
      Stm25pLogP$Sector$write(client, Stm25pLogP$calcAddr(client, write_addr), buf, len);
    }
}

# 188 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static  error_t Stm25pSectorP$Sector$write(uint8_t id, stm25p_addr_t addr, 
uint8_t *buf, 
stm25p_len_t len)
#line 190
{

  Stm25pSectorP$m_state = Stm25pSectorP$S_WRITE;
  Stm25pSectorP$m_addr = addr;
  Stm25pSectorP$m_buf = buf;
  Stm25pSectorP$m_len = Stm25pSectorP$m_cur_len = len;

  return Stm25pSectorP$Spi$pageProgram(Stm25pSectorP$physicalAddr(id, addr), buf, 
  Stm25pSectorP$calcWriteLen(addr));
}

# 156 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpiP.nc"
static   error_t Stm25pSpiP$Spi$pageProgram(stm25p_addr_t addr, uint8_t *buf, 
stm25p_len_t len)
#line 157
{
  Stm25pSpiP$m_cmd[0] = Stm25pSpiP$S_PAGE_PROGRAM;
  Stm25pSpiP$m_addr = addr;
  Stm25pSpiP$m_buf = buf;
  Stm25pSpiP$m_len = len;
  return Stm25pSpiP$newRequest(TRUE, 4);
}

# 158 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSectorP.nc"
static stm25p_len_t Stm25pSectorP$calcWriteLen(stm25p_addr_t addr)
#line 158
{
  stm25p_len_t len = STM25P_PAGE_SIZE - (addr & STM25P_PAGE_MASK);

#line 160
  return Stm25pSectorP$m_cur_len < len ? Stm25pSectorP$m_cur_len : len;
}

# 187 "/opt/tinyos-2.x/tos/chips/stm25p/Stm25pSpiP.nc"
static void Stm25pSpiP$releaseAndRequest(void)
#line 187
{
  Stm25pSpiP$SpiResource$release();
  Stm25pSpiP$SpiResource$request();
}

#line 249
static void Stm25pSpiP$signalDone(error_t error)
#line 249
{
  Stm25pSpiP$m_is_writing = FALSE;
  switch (Stm25pSpiP$m_cmd[0]) {
      case Stm25pSpiP$S_READ: 
        if (Stm25pSpiP$m_computing_crc) {
            Stm25pSpiP$m_computing_crc = FALSE;
            Stm25pSpiP$Spi$computeCrcDone(Stm25pSpiP$m_crc, Stm25pSpiP$m_addr, Stm25pSpiP$m_len, error);
          }
        else {
            Stm25pSpiP$Spi$readDone(Stm25pSpiP$m_addr, Stm25pSpiP$m_buf, Stm25pSpiP$m_len, error);
          }
      break;
      case Stm25pSpiP$S_PAGE_PROGRAM: 
        Stm25pSpiP$Spi$pageProgramDone(Stm25pSpiP$m_addr, Stm25pSpiP$m_buf, Stm25pSpiP$m_len, error);
      break;
      case Stm25pSpiP$S_SECTOR_ERASE: 
        Stm25pSpiP$Spi$sectorEraseDone(Stm25pSpiP$m_addr >> STM25P_SECTOR_SIZE_LOG2, error);
      break;
      case Stm25pSpiP$S_BULK_ERASE: 
        Stm25pSpiP$Spi$bulkEraseDone(error);
      break;
    }
}

#line 192
static   void Stm25pSpiP$SpiPacket$sendDone(uint8_t *tx_buf, uint8_t *rx_buf, 
uint16_t len, error_t error)
#line 193
{

  int i;

  switch (Stm25pSpiP$m_cmd[0]) {

      case Stm25pSpiP$S_READ: 
        if (tx_buf == Stm25pSpiP$m_cmd) {
            Stm25pSpiP$SpiPacket$send((void *)0, Stm25pSpiP$m_buf, Stm25pSpiP$m_len);
            break;
          }
        else {
#line 204
          if (Stm25pSpiP$m_computing_crc) {
              for (i = 0; i < len; i++) 
                Stm25pSpiP$m_crc = crcByte(Stm25pSpiP$m_crc, Stm25pSpiP$m_crc_buf[i]);
              Stm25pSpiP$m_cur_addr += len;
              Stm25pSpiP$m_cur_len -= len;
              if (Stm25pSpiP$m_cur_len) {
                  Stm25pSpiP$SpiPacket$send((void *)0, Stm25pSpiP$m_crc_buf, Stm25pSpiP$calcReadLen());
                  break;
                }
            }
          }
#line 214
      Stm25pSpiP$CSN$set();
      Stm25pSpiP$signalDone(SUCCESS);
      break;

      case Stm25pSpiP$S_PAGE_PROGRAM: 
        if (tx_buf == Stm25pSpiP$m_cmd) {
            Stm25pSpiP$SpiPacket$send(Stm25pSpiP$m_buf, (void *)0, Stm25pSpiP$m_len);
            break;
          }


      case Stm25pSpiP$S_SECTOR_ERASE: case Stm25pSpiP$S_BULK_ERASE: 
          Stm25pSpiP$CSN$set();
      Stm25pSpiP$m_is_writing = TRUE;
      Stm25pSpiP$releaseAndRequest();
      break;

      default: 
        break;
    }
}

# 80 "/opt/tinyos-2.x/tos/system/crc.h"
static uint16_t crcByte(uint16_t crc, uint8_t b)
#line 80
{
  crc = (uint8_t )(crc >> 8) | (crc << 8);
  crc ^= b;
  crc ^= (uint8_t )(crc & 0xff) >> 4;
  crc ^= crc << 12;
  crc ^= (crc & 0xff) << 5;
  return crc;
}

# 62 "/opt/tinyos-2.x/tos/lib/timer/VirtualizeTimerC.nc"
static void /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$fireTimers(uint32_t now)
{
  uint8_t num;

  for (num = 0; num < /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$NUM_TIMERS; num++) 
    {
      /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer_t *timer = &/*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$m_timers[num];

      if (timer->isrunning) 
        {
          uint32_t elapsed = now - timer->t0;

          if (elapsed >= timer->dt) 
            {
              if (timer->isoneshot) {
                timer->isrunning = FALSE;
                }
              else {
#line 79
                timer->t0 += timer->dt;
                }
              /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$Timer$fired(num);
            }
        }
    }
  /*HilTimerMilliC.VirtualizeTimerC*/VirtualizeTimerC$0$updateFromTimer$postTask();
}

# 136 "/opt/tinyos-2.x/tos/lib/timer/TransformAlarmC.nc"
static   void /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$Alarm$startAt(/*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type t0, /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$to_size_type dt)
{
  { __nesc_atomic_t __nesc_atomic = __nesc_atomic_start();
    {
      /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_t0 = t0;
      /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$m_dt = dt;
      /*HilTimerMilliC.AlarmMilli32C.Transform*/TransformAlarmC$0$set_alarm();
    }
#line 143
    __nesc_atomic_end(__nesc_atomic); }
}

# 96 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart0P.nc"
__attribute((wakeup)) __attribute((interrupt(18)))  void sig_UART0RX_VECTOR(void)
#line 96
{
  uint8_t temp = U0RXBUF;

#line 98
  HplMsp430Usart0P$Interrupts$rxDone(temp);
}

# 147 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static   bool /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ArbiterInfo$inUse(void)
#line 147
{
  /* atomic removed: atomic calls only */
#line 148
  {
    if (/*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$state == /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$RES_CONTROLLED) 
      {
        unsigned char __nesc_temp = 
#line 150
        FALSE;

#line 150
        return __nesc_temp;
      }
  }
#line 152
  return TRUE;
}






static   uint8_t /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$ArbiterInfo$userId(void)
#line 160
{
  /* atomic removed: atomic calls only */
#line 161
  {
    if (/*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$state != /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$RES_BUSY) 
      {
        unsigned char __nesc_temp = 
#line 163
        /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$NO_RES;

#line 163
        return __nesc_temp;
      }
#line 164
    {
      unsigned char __nesc_temp = 
#line 164
      /*Msp430UsartShare0P.ArbiterC.Arbiter*/ArbiterP$1$resId;

#line 164
      return __nesc_temp;
    }
  }
}

# 101 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart0P.nc"
__attribute((wakeup)) __attribute((interrupt(16)))  void sig_UART0TX_VECTOR(void)
#line 101
{
  if (HplMsp430Usart0P$HplI2C$isI2C()) {
    HplMsp430Usart0P$I2CInterrupts$fired();
    }
  else {
#line 105
    HplMsp430Usart0P$Interrupts$txDone();
    }
}

# 96 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart1P.nc"
__attribute((wakeup)) __attribute((interrupt(6)))  void sig_UART1RX_VECTOR(void)
#line 96
{
  uint8_t temp = U1RXBUF;

#line 98
  HplMsp430Usart1P$Interrupts$rxDone(temp);
}

# 147 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static   bool /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ArbiterInfo$inUse(void)
#line 147
{
  /* atomic removed: atomic calls only */
#line 148
  {
    if (/*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$state == /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$RES_CONTROLLED) 
      {
        unsigned char __nesc_temp = 
#line 150
        FALSE;

#line 150
        return __nesc_temp;
      }
  }
#line 152
  return TRUE;
}

# 402 "/opt/tinyos-2.x/tos/lib/serial/SerialP.nc"
static void SerialP$rx_state_machine(bool isDelimeter, uint8_t data)
#line 402
{

  switch (SerialP$rxState) {

      case SerialP$RXSTATE_NOSYNC: 
        if (isDelimeter) {
            SerialP$rxInit();
            SerialP$rxState = SerialP$RXSTATE_PROTO;
          }
      break;

      case SerialP$RXSTATE_PROTO: 
        if (!isDelimeter) {
            SerialP$rxCRC = crcByte(SerialP$rxCRC, data);
            SerialP$rxState = SerialP$RXSTATE_TOKEN;
            SerialP$rxProto = data;
            if (!SerialP$valid_rx_proto(SerialP$rxProto)) {
              goto nosync;
              }
            if (SerialP$rxProto != SERIAL_PROTO_PACKET_ACK) {
                goto nosync;
              }
            if (SerialP$ReceiveBytePacket$startPacket() != SUCCESS) {
                goto nosync;
              }
          }
      break;

      case SerialP$RXSTATE_TOKEN: 
        if (isDelimeter) {
            goto nosync;
          }
        else {
            SerialP$rxSeqno = data;
            SerialP$rxCRC = crcByte(SerialP$rxCRC, SerialP$rxSeqno);
            SerialP$rxState = SerialP$RXSTATE_INFO;
          }
      break;

      case SerialP$RXSTATE_INFO: 
        if (SerialP$rxByteCnt < SerialP$SERIAL_MTU) {
            if (isDelimeter) {
                if (SerialP$rxByteCnt >= 2) {
                    if (SerialP$rx_current_crc() == SerialP$rxCRC) {
                        SerialP$ReceiveBytePacket$endPacket(SUCCESS);
                        SerialP$ack_queue_push(SerialP$rxSeqno);
                        goto nosync;
                      }
                    else {
                        goto nosync;
                      }
                  }
                else {
                    goto nosync;
                  }
              }
            else {
                if (SerialP$rxByteCnt >= 2) {
                    SerialP$ReceiveBytePacket$byteReceived(SerialP$rx_buffer_top());
                    SerialP$rxCRC = crcByte(SerialP$rxCRC, SerialP$rx_buffer_pop());
                  }
                SerialP$rx_buffer_push(data);
                SerialP$rxByteCnt++;
              }
          }
        else 

          {
            goto nosync;
          }
      break;

      default: 
        goto nosync;
    }
  goto done;

  nosync: 

    SerialP$rxInit();
  SerialP$SerialFrameComm$resetReceive();
  SerialP$ReceiveBytePacket$endPacket(FAIL);
  if (SerialP$offPending) {
      SerialP$rxState = SerialP$RXSTATE_INACTIVE;
      SerialP$testOff();
    }
  else {
    if (isDelimeter) {
        SerialP$rxState = SerialP$RXSTATE_PROTO;
      }
    }
  done: ;
}

# 285 "/opt/tinyos-2.x/tos/lib/serial/SerialDispatcherP.nc"
static   void /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$ReceiveBytePacket$endPacket(error_t result)
#line 285
{
  uint8_t postsignalreceive = FALSE;

  /* atomic removed: atomic calls only */
#line 287
  {
    if (!/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTaskPending && result == SUCCESS) {
        postsignalreceive = TRUE;
        /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTaskPending = TRUE;
        /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTaskType = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$recvType;
        /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTaskWhich = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState.which;
        /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTaskBuf = (message_t *)/*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveBuffer;
        /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTaskSize = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$recvIndex;
        /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveBufferSwap();
        /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveState.state = /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$RECV_STATE_IDLE;
      }
  }
  if (postsignalreceive) {
      /*SerialDispatcherC.SerialDispatcherP*/SerialDispatcherP$0$receiveTask$postTask();
    }
}

# 160 "/opt/tinyos-2.x/tos/system/ArbiterP.nc"
static   uint8_t /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$ArbiterInfo$userId(void)
#line 160
{
  /* atomic removed: atomic calls only */
#line 161
  {
    if (/*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$state != /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$RES_BUSY) 
      {
        unsigned char __nesc_temp = 
#line 163
        /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$NO_RES;

#line 163
        return __nesc_temp;
      }
#line 164
    {
      unsigned char __nesc_temp = 
#line 164
      /*Msp430UsartShare1P.ArbiterC.Arbiter*/ArbiterP$2$resId;

#line 164
      return __nesc_temp;
    }
  }
}

# 101 "/opt/tinyos-2.x/tos/chips/msp430/usart/HplMsp430Usart1P.nc"
__attribute((wakeup)) __attribute((interrupt(4)))  void sig_UART1TX_VECTOR(void)
#line 101
{
  HplMsp430Usart1P$Interrupts$txDone();
}

# 92 "/opt/tinyos-2.x/tos/lib/serial/HdlcTranslateC.nc"
static   error_t HdlcTranslateC$SerialFrameComm$putData(uint8_t data)
#line 92
{
  if (data == HDLC_CTLESC_BYTE || data == HDLC_FLAG_BYTE) {
      HdlcTranslateC$state.sendEscape = 1;
      HdlcTranslateC$txTemp = data ^ 0x20;
      HdlcTranslateC$m_data = HDLC_CTLESC_BYTE;
    }
  else {
      HdlcTranslateC$m_data = data;
    }
  return HdlcTranslateC$UartStream$send(&HdlcTranslateC$m_data, 1);
}

