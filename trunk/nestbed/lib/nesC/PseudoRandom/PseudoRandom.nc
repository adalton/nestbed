/* $Id$ */

interface PseudoRandom {
    command void     seed(uint32_t value);
    command uint16_t rand();
}
