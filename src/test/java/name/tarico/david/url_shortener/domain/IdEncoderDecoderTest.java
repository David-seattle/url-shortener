package name.tarico.david.url_shortener.domain;

import name.tarico.david.url_shortener.domain.IdEncoderDecoder;
import org.junit.Test;

import static org.junit.Assert.*;

public class IdEncoderDecoderTest {

    @Test(expected = IllegalArgumentException.class)
    public void encodeDisallowsZero() {
        IdEncoderDecoder.encode(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void encodeDisallowsNegativeNumbers() {
        IdEncoderDecoder.encode(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeDisallowsNull() {
        IdEncoderDecoder.decode("-");
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeDisallowsEmptyString() {
        IdEncoderDecoder.decode("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeDisallowsDollarSign() {
        //Ensure it validates against non alpha-numeric characters below the digits
        IdEncoderDecoder.decode("$");
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeDisallowsEquals() {
        //Ensure it validates against non alpha-numeric characters between digits and letters
        IdEncoderDecoder.decode("+");
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeDisallowsCurlyBrace() {
        //Ensure it properly validates against non alpha-numeric characters between lower and uppercase letters
        IdEncoderDecoder.decode("{");
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeDisallowsDash() {
        //Ensure it properly validates against non alpha-numeric characters above the letters
        IdEncoderDecoder.decode("-");
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeDisallowsExtendedAscii() {
        //Ensure it properly validates against non alpha-numeric characters above the letters
        IdEncoderDecoder.decode("" + Character.MAX_VALUE);
    }

    @Test
    public void encodesSmallIdsAsSingleCharacter() {
        long id = 1;
        String idStr = IdEncoderDecoder.encode(id);
        assertEquals("1", idStr);
        assertEquals(id, IdEncoderDecoder.decode(idStr));

        id = 9;
        idStr = IdEncoderDecoder.encode(id);
        assertEquals("9", idStr);
        assertEquals(id, IdEncoderDecoder.decode(idStr));

        id = 10;
        idStr = IdEncoderDecoder.encode(id);
        assertEquals("a", idStr);
        assertEquals(id, IdEncoderDecoder.decode(idStr));

        id = 35;
        idStr = IdEncoderDecoder.encode(id);
        assertEquals("z", idStr);
        assertEquals(id, IdEncoderDecoder.decode(idStr));

        id = 36;
        idStr = IdEncoderDecoder.encode(id);
        assertEquals("A", idStr);
        assertEquals(id, IdEncoderDecoder.decode(idStr));

        id = 61;
        idStr = IdEncoderDecoder.encode(id);
        assertEquals("Z", idStr);
        assertEquals(id, IdEncoderDecoder.decode(idStr));id = 61;
    }


    @Test
    public void encodesIdsWithTwoCharacters() {
        long id = 62;
        String idStr = IdEncoderDecoder.encode(id);
        assertEquals("10", idStr);
        assertEquals(id, IdEncoderDecoder.decode(idStr));

        id = 123;
        idStr = IdEncoderDecoder.encode(id);
        assertEquals("1Z", idStr);
        assertEquals(id, IdEncoderDecoder.decode(idStr));
    }

    @Test
    public void encodesReallyLargeIds() {
        long id = Long.MAX_VALUE;
        String idStr = IdEncoderDecoder.encode(id);
        assertEquals("aZl8N0y58M7", idStr);
        assertEquals(id, IdEncoderDecoder.decode(idStr));
    }

}