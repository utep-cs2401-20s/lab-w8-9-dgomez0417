import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

class AminoAcidLLTest {
    
    @Test
        ///failed I am not sure why it is failing it shows line 13 is incorrect.
    void aminoAcidCompare(){
        AminoAcidLL one = AminoAcidLL.createFromRNASequence("GUAGGUAAU");
        AminoAcidLL two = AminoAcidLL.createFromRNASequence("GUAGGUAAU");
        one = AminoAcidLL.sort(one);
        two = AminoAcidLL.sort(two);

        int exc = two.aminoAcidCompare(one);
        int tDiff = 1;
        assertEquals(exc, tDiff);
    }

    @Test
        //failed I am not sure why it is failing it shows line 27 is incorrect.
    void codonCompare(){
        AminoAcidLL one = AminoAcidLL.createFromRNASequence("GUAGGUAAU");
        AminoAcidLL two = AminoAcidLL.createFromRNASequence("GUAGGUAAU");
        one = AminoAcidLL.sort(one);
        two = AminoAcidLL.sort(two);

        int exc = two.aminoAcidCompare(one);
        int usage = 0;
        assertNotEquals(exc, usage);
    }

    @Test
    // test passed
    // code was able to take a string of codons and return the correct amino acid
    void aminoAcidList() {
        String input = "GUAGGUAAU";
        AminoAcidLL list = AminoAcidLL.createFromRNASequence(input);
        char [] actual = list.aminoAcidList();
        char [] expected = {'V','G','N'};
        assertArrayEquals(expected, actual);
    }
    @Test
        // test passed
        // code was able to take a string of codons and return the correct amino acid
    void aminoAcidList2() {
        String input = "UCACAGCACCCGCUGCGG";
        AminoAcidLL list = AminoAcidLL.createFromRNASequence(input);
        char [] actual = list.aminoAcidList();
        char [] expected = {'S','Q','H','P','L','R'};
        assertArrayEquals(expected, actual);
    }

    @Test
        // test failed and did what it was supposed to do
        // code was not able to run since there are letter in there that are not considered part of the codons
    void aminoAcidList3() {
        String input = "UCTCAHCACCCRQUGCPG";
        AminoAcidLL list = AminoAcidLL.createFromRNASequence(input);
        char [] actual = list.aminoAcidList();
        char [] expected = {'S','Q','H','P','L','R'};
        assertArrayEquals(expected, actual);
    }

    @Test
    void aminoAcidCounts() {
        String input = "GUAGGUAAU";
        AminoAcidLL list = AminoAcidLL.createFromRNASequence("GUAGGUAAU");
        int [] actual = list.aminoAcidCounts();
        int [] expected = {1,1,1};
        assertArrayEquals(expected, actual);
    }

    // failed
    // null pointer exception
    @Test
    void isSorted() {
        AminoAcidLL test = AminoAcidLL.createFromRNASequence("GUAGGUAAU");
        AminoAcidLL.sort(test);

        boolean exc = test.isSorted();
        assertEquals(exc, false);
    }

    //passed
    // was able to take a string sequence and split them up into groups of 3 to be looked at as codons
    @Test
    void createFromRNASequence(){
        String input = "UCACAGCACCCGCUGCGG";
        AminoAcidLL list = AminoAcidLL.createFromRNASequence(input);
        String [] actual = list.aminoAcidList();
        String [] expected = {"UCA","CAG","CAC","CCG","CUG","CGG"};
        assertArrayEquals(expected, actual);
    }


    //passed
    // sorting method will not work when the linked list is empty.
    public boolean isEmpty(){
        AminoAcidLL test = new AminoAcidLL();
        if(test == null){
            return false;
        }
        return true;
    }
    @Test
    void sort() {
        assertTrue(this::isEmpty);
    }

    // test failed
    //says there is a null pointer error
    @Test
    void sort2() {
        AminoAcidLL test = new AminoAcidLL("GUAGGUAAU");
        AminoAcidLL expect = new AminoAcidLL("AAAGGGUUU");
        AminoAcidLL.sort(test);
        assertEquals(test, expect);
    }
}