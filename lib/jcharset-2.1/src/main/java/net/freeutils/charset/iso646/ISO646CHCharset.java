/*
 *  Copyright © 2005-2019 Amichai Rothman
 *
 *  This file is part of JCharset - the Java Charset package.
 *
 *  JCharset is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  JCharset is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with JCharset.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  For additional info see http://www.freeutils.net/source/jcharset/
 */

package net.freeutils.charset.iso646;

import net.freeutils.charset.ByteLookupCharset;

/**
 * The <b>ISO646CHCharset</b> class handles the encoding and decoding of the
 * ISO646-CH national variant of the ISO/IEC 646 charset.
 *
 * @author Amichai Rothman
 * @since  2015-08-18
 */
public class ISO646CHCharset extends ByteLookupCharset {

    static final String NAME = "ISO646-CH";

    static final String[] ALIASES = {};

    static final int[] BYTE_TO_CHAR = mutate(ISO646USCharset.BYTE_TO_CHAR,
        new int[] { 0x23, 0x40, 0x5B, 0x5C, 0x5D, 0x5E, 0x5F, 0x60, 0x7B, 0x7C, 0x7D, 0x7E },
        new int[] { 0xF9, 0xE0, 0xE9, 0xE7, 0xEA, 0xEE, 0xE8, 0xF4, 0xE4, 0xF6, 0xFC, 0xFB });

    static final int[][] CHAR_TO_BYTE = createInverseLookupTable(BYTE_TO_CHAR);

    /**
     * Constructs an instance of the ISO646CHCharset.
     */
    public ISO646CHCharset() {
        super(NAME, ALIASES, BYTE_TO_CHAR, CHAR_TO_BYTE);
    }

}