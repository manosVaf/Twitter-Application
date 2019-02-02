
public class GreekStemmer {

    public String stem(String term) {

        // Check if the term is stemmable
        //if (!isStemmable(term)){
        //    return term;
        //}

        //Check if term is numeric
      //DIKH MOY ALLAGH GIATI XTYPAGE TO INDEXfILES
      //  if (term.matches("^[+-]?(\\d+(\\.\\d*)?|\\.\\d+)([eE][+-]?\\d+)?$"))
      //      return "";

        // Remove first level suffixes only if the term is 4 letters or more
        if (term.length() >= 4) {

            // Remove the 3 letter suffixes
            if (term.endsWith("���")||
                term.endsWith("���")||
                term.endsWith("���")||
                term.endsWith("���"))
                term = term.substring(0,term.length() - 3);

            // Remove the 2 letter suffixes
            else if (term.endsWith("��")||
                     term.endsWith("��")||
                     term.endsWith("��")||
                     term.endsWith("��")||
                     term.endsWith("��")||
                     term.endsWith("��")||
                     term.endsWith("��")||
                     term.endsWith("��")||
                     term.endsWith("��")||
                     term.endsWith("��")||
                     term.endsWith("��")||
                     term.endsWith("��")||
                     term.endsWith("��"))

                     term = term.substring(0,term.length() - 2);

            // Remove the 1 letter suffixes
            else if (term.endsWith("�")||
                     term.endsWith("�")||
                     term.endsWith("�")||
                     term.endsWith("�")||
                     term.endsWith("�")||
                     term.endsWith("�")||
                     term.endsWith("�"))

                     term = term.substring(0,term.length() - 1);
        }
        return term;
    }
   }