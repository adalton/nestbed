/* $Id$ */

module PseudoRandomM {
    provides interface PseudoRandom;
}

implementation {
    uint32_t multiplier = 0x5DECE66D;
    uint32_t addend     = 0xB;
    uint32_t mask       = (1L << 24) - 1;
    uint32_t seed;

    command void PseudoRandom.seed(uint32_t value) {
        seed = (value ^ multiplier) & mask;
    }

    command uint16_t PseudoRandom.rand() {
        seed = (seed * multiplier + addend) & mask;
        return (uint16_t) seed;
    }
}
