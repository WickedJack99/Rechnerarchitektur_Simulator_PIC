

JCharset - Java Charset package 2.1
===================================

Copyright © 2005-2019 Amichai Rothman



1. What is the Java Charset package?

    The Java Charset package is an open-source implementation of character
    sets that were missing from the standard Java platform.

    It has been in use in many production systems around the world for over a
    decade, including products by small start-ups, large open-source service
    providers, and well-known multinational corporations.


2. How do I use the Java Charset package?

    The Java Charset package is written in pure Java, runs on JDK 1.5 or later,
    and requires no special installation - just add the jar file to your
    classpath, or place it in any of the usual extension directories.

    It is also available on Maven Central at the artifact coordinates
    net.freeutils:jcharset:2.1.

    The JVM will recognize the supported character sets automatically, and they
    will be available anywhere character sets are used in the Java platform.

    As an example, you can take a look at java.lang.String's constructor and
    getBytes() method, both of which have an overloaded version that receives
    a charset name as an argument.

    A command-line utility is included which supports converting files
    between charsets. For help on usage and available options, run it using
    the command 'java -jar jcharset-2.1.jar -h'.

    Note: Some web/mail containers run each application in its own JVM context.
    In this case check the container documentation for information on where and
    how to configure the classpath, such as in WEB-INF/lib, shared/lib,
    jre/lib/ext, etc. You may need to restart the server for changes to take
    effect. However, if you use Oracle's JRE, it will work only if you put it in
    the jre/lib/ext extension directory, or in the container's classpath.
    This is due to a bug in Oracle's JRE implementation
    (http://bugs.java.com/bugdatabase/view_bug.do?bug_id=4619777).

3. Which charsets are supported?

    "UTF-7" (a.k.a. "UTF7", "UNICODE-1-1-UTF-7", "csUnicode11UTF7",
                    "UNICODE-2-0-UTF-7")
        The 7-bit Unicode character encoding defined in RFC 2152.
        The O-set characters are encoded as a shift sequence.
        Both O-set flavors (direct and shifted) are decoded.

    "UTF-7-OPTIONAL" (a.k.a. "UTF-7O", "UTF7O", "UTF-7-O")
        The 7-bit Unicode character encoding defined in RFC 2152.
        The O-set characters are directly encoded.
        Both O-set flavors (direct and shifted) are decoded.

    "SCGSM" (a.k.a. "GSM-default-alphabet", "GSM_0338", "GSM_DEFAULT",
                    "GSM7", "GSM-7BIT")
        The GSM default charset as specified in GSM 03.38, used in SMPP for
        encoding SMS text messages.

        Additional flavors of the GSM charset are "CCGSM", "SCPGSM", "CCPGSM",
        "CRSCPGSM" and "CRCCPGSM": The CC prefix signifies mapping the Latin
        capital letter C with cedilla character, the SC prefix signifies
        mapping the Latin small letter c with cedilla character, the P prefix
        signifies the packed form (8 characters packed in 7 bytes), and the
        CR prefix signifies padding with CR instead of zeros to avoid ambiguity,
        all as specified by the spec. See javadocs for details.

    "hp-roman8" (a.k.a. "roman8", "r8", "csHPRoman8", "X-roman8")
        The HP Roman-8 charset, as provided in RFC 1345.

    ISO/IEC 646 National Variants:
        "ISO646-CA" ("ISO-IR-121")
        "ISO646-CA2" ("ISO-IR-122")
        "ISO646-CH"
        "ISO646-CN" ("ISO-IR-57")
        "ISO646-CU" ("ISO-IR-151")
        "ISO646-DE" ("ISO-IR-21", "DIN_66003")
        "ISO646-DK"
        "ISO646-ES" ("ISO-IR-17")
        "ISO646-ES2" ("ISO-IR-85")
        "ISO646-FI" ("ISO646-SE", "ISO-IR-10")
        "ISO646-FR" ("ISO-IR-69")
        "ISO646-FR1" ("ISO-IR-25")
        "ISO646-GB" ("ISO-IR-4")
        "ISO646-HU" ("ISO-IR-86")
        "ISO646-IE" ("ISO-IR-207")
        "ISO646-INV" ("ISO-IR-170")
        "ISO646-IRV" ("ISO-IR-2", "ISO_646.IRV:1983")
        "ISO646-IS"
        "ISO646-IT" ("ISO-IR-15")
        "ISO646-JAO" ("ISO646-JP-OCR-B", "ISO-IR-92")
        "ISO646-JP" ("ISO-IR-14")
        "ISO646-KR"
        "ISO646-MT"
        "ISO646-NO" ("ISO-IR-60")
        "ISO646-NO2" ("ISO-IR-61")
        "ISO646-PT" ("ISO-IR-16")
        "ISO646-PT2" ("ISO-IR-84")
        "ISO646-SE2" ("ISO-IR-11")
        "ISO646-T61" ("ISO-IR-102")
        "ISO646-TW"
        "ISO646-US" ("ISO-IR-6", "ISO_646.irv:1991")
        "ISO646-YU" ("ISO-IR-141")

    "ISO-8859-8-BIDI" (a.k.a. "csISO88598I", "ISO-8859-8-I", "ISO_8859-8-I",
                              "csISO88598E", "ISO-8859-8-E", "ISO_8859-8-E")
        The ISO 8859-8 charset implementation exists in the standard JRE.
        However, it is lacking the i/e aliases, which specify whether
        bidirectionality is implicit or explicit. The charsets conversions
        themselves are similar. This charset complements the standard one.

    "ISO-8859-6-BIDI" (a.k.a. "csISO88596I", "ISO-8859-6-I", "ISO_8859-6-I",
                              "csISO88596E", "ISO-8859-6-E", "ISO_8859-6-E")
        The ISO 8859-6 charset implementation exists in the standard JRE.
        However, it is lacking the i/e aliases, which specify whether
        bidirectionality is implicit or explicit. The charsets conversions
        themselves are similar. This charset complements the standard one.

    "KOI8-U" (a.k.a. "KOI8-RU", "KOI8_U")
        The KOI8-U Ukrainian charset, as defined in RFC 2319.

    "KZ-1048" (a.k.a. "STRK1048-2002", "RK1048", "csKZ1048")
        The KZ-1048 charset, which is the Kazakhstan national standard.

    "MIK"
        The MIK cyrillic code page, commonly used by DOS applications
        in Bulgaria.


4. License

    The Java Charset package is provided under the GNU General Public
    License agreement. Please read the full license agreement in the
    included LICENSE.gpl.txt file.

    For non-GPL commercial licensing please contact the address below.


5. Contact

    Please write to support@freeutils.net with any bugs, suggestions, fixes,
    contributions, or just to drop a good word and let me know you've found
    this package useful and you'd like it to keep being maintained.

    Updates and additional info can be found at
    http://www.freeutils.net/source/jcharset/
