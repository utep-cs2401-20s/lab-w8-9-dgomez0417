class AminoAcidLL{
  char aminoAcid;
  String[] codons;
  int[] counts;
  AminoAcidLL next;

  AminoAcidLL(){

  }


  /********************************************************************************************/
  /* Creates a new node, with a given amino acid/codon 
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  AminoAcidLL(String inCodon){
    aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon); // amino acid is base on those 3 string letters
    codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid); // codons are for amoino acids
    counts = new int [codons.length];
    next = null;
  }

  public void incrCodons(String inCodon){ // method used to increment how many times a letter is used
    for(int i = 0; i < codons.length; i++){ // looks trough the list
      if(codons[i].equals(inCodon.toUpperCase())){ //Makes sure that the letters are uppercase in order to be counted
        counts[i]++; // increments
      }
    }
  }

  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops, 
   * if not passes the task to the next node. 
   * If there is no next node, add a new node to the list that would contain the codon. 
   */
  private void addCodon(String inCodon){
    if(AminoAcidResources.getAminoAcidFromCodon(inCodon) == aminoAcid){ // base case
      incrCodons(inCodon);
    }
    else if(next != null){
      next.addCodon(inCodon); // check next node
    }
    else{
      next = new AminoAcidLL(inCodon);
    }
  }


  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount(){
    int sum = 0;
    for(int i = 0; i < counts.length; i++){ // goes trough the count array a sums it all up
      sum += counts[i];
    }
    return sum;
  }

  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int totalDiff(AminoAcidLL inList){

    return Math.abs(totalCount() - inList.totalCount());
  }


  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int codonDiff(AminoAcidLL inList){
    int diff = 0;
    for(int i=0; i<codons.length; i++){  // goes trough the codons
      diff += Math.abs(counts[i] - inList.counts[i]); // if there is a difference in the counts between two similar codons then they subtract and get the difference
    }
    return diff;
  }

  /********************************************************************************************/
  /* Recursive method that finds the differences in **Amino Acid** counts. 
   * the list *must* be sorted to use this method */
  public int aminoAcidCompare(AminoAcidLL inList){
    //find the difference between these two list
    // what if the list is empty
    //what if the next one is empty
    // if they have the same counts whats the difference
    // or if they dont have to same count what are they and what is the difference, which one is less than the other, use the codon difference method
    int difference = 0;
    if(!inList.isSorted()){ // checking to see if the list is sorted so that it cant contiune or not
      return -1;
    }
    if(inList == null){ // if the list is empty
      difference += totalCount();// itll still take the difference of the other list
      if(next != null){ // if the list is not null
        difference += next.aminoAcidCompare(inList.next); // then itll keep going and till the end and get the difference
      }
    }
    else if(inList.aminoAcid == aminoAcid){ // checks to see if the amino acids are the same then they will subtract
      difference = (Math.abs(totalCount() -inList.totalCount())); // being subtract
      if(next != null){// when it reaches null then it returns difference
        difference += next.aminoAcidCompare(inList.next);
      }
      if(next == null && inList.next != null){ // if one is null and the other list isnt then itll still keep going
        difference += aminoAcidCompare(inList.next);
      }
    }
    else if(next == null && aminoAcid < inList.aminoAcid){// comparing each one of the counts and making sure that we dont end up with a negative number
        difference += totalCount();
        if(next != null){
          difference += next. aminoAcidCompare(inList);
        }
    }
    else if(next == null || aminoAcid > aminoAcid){// comparing each one of the counts and making sure that we dont end up with a negative number
      difference += inList.totalCount();
      if(inList.next != null){
        difference += aminoAcidCompare(inList.next);
      }
    }

    return difference;
  }

  /********************************************************************************************/
  /* Same ad above, but counts the codon usage differences
   * Must be sorted. */

  public int codonCompare(AminoAcidLL inList){
    int usage = 0;
    if(!inList.isSorted()){// checking to see if the list is sorted so that it cant contiune or not
      return -1;
    }
    if(inList == null){// if the list is empty
      usage += totalCount();// itll still take the usage of the other list
      if(next != null){// if the list is not null
        usage += next.aminoAcidCompare(inList.next); // then itll keep going and till the end and get the difference
      }
    }
    else if(inList.aminoAcid == aminoAcid){ // checks to see if the amino acids are the same then they will subtract
      usage = (Math.abs(totalCount() -inList.totalCount()));// being subtract
      if(next != null){ // when it reaches null then it returns usage
        usage += next.aminoAcidCompare(inList.next);
      }
      if(next == null && inList.next != null){// if one is null and the other list isnt then itll still keep going
        usage += aminoAcidCompare(inList.next);
      }
    }
    else if(next == null && aminoAcid < inList.aminoAcid){ // comparing each one of the counts and making sure that we dont end up with a negative number
      usage += totalCount();
      if(next != null){
        usage += next. aminoAcidCompare(inList);
      }
    }
    else if(next == null || aminoAcid > aminoAcid){// comparing each one of the counts and making sure that we dont end up with a negative number
      usage += inList.totalCount();
      if(inList.next != null){
        usage += aminoAcidCompare(inList.next);
      }
    }
    return usage;
  }


  /********************************************************************************************/
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList(){
    if(next == null){
      return new char[]{aminoAcid}; // will return the char from the RNA sequence
    }
    char[] arr = next.aminoAcidList(); // holds chars

    char[] newarr = new char[arr.length + 1];// new array to hold the final array

    for(int i = 0; i < arr.length; i++){ // copying array into the new array
      newarr[i + 1] = arr[i];
    }

    newarr[0] = aminoAcid;
    return newarr;
  }

  /********************************************************************************************/
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts(){ //amino acid number of codons // going looking entire list // returning total count of amino acids counts
    if(next == null){
      return new int[]{totalCount()}; // will return the total count at the end of the list
    }
    int[] arr = next.aminoAcidCounts(); // creates the array to hold the counts

    int[] newarr = new int[arr.length + 1]; //created the new array with the complete counts

    for(int i = 0; i < arr.length; i++){ // copies array into new array
      newarr[i + 1] = arr[i];
    }

    newarr[0] = totalCount();
    return newarr;
  }

  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted(){
    // just current node less the next node
    if(aminoAcid < next.aminoAcid){
      return true;
    }
    return false;
  }


  /********************************************************************************************/
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence){
    AminoAcidLL list = null;
    //string has to be 3
    if(inSequence.length() >=3 && AminoAcidResources.getAminoAcidFromCodon(inSequence.substring(0,2)) != '*');{
      list = new AminoAcidLL(inSequence.substring(0,3)); // creates first codon with three letters

      boolean cont = true;

      for(int i =3; i < inSequence.length() - 2 && cont; i +=3){// creates the rest of the sets of 3 from the  RNA
        if(AminoAcidResources.getAminoAcidFromCodon(inSequence.substring(i , i + 3)) != '*'){
          list.addCodon(inSequence.substring(i, i + 3));
        }
        else{
          cont = false;
        }
      }
      return list;
    }
  }

  /********************************************************************************************/
  /* sorts a list by amino acid character*/

  public static AminoAcidLL sort(AminoAcidLL inList){
    AminoAcidLL i = inList;

    while(i != null) { // will keep the sorting method going till it reaches the end
      AminoAcidLL max = i;

      for (AminoAcidLL j = i; j.next != null; j = j.next) { // making j the iterater
        if (max.aminoAcid < j.aminoAcid) { // the tells us that the node after max is the greatest one
          max = j;
        }
      }
      AminoAcidLL j = inList; // points the iterater to the new head
      while (j.next != max) {
        j = j.next;
      }

      if (i == max) { //makes sure that head move to the correct one and no stay on the only mad
        i = i.next;
      }

      // in these next three lines here i am swapping the pointers of the linked list
      j.next = max.next;
      max.next = inList;
      inList = max;
    }

    return inList;
  }
}