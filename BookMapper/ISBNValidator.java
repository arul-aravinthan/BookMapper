public class ISBNValidator implements IISBNValidator{
    /**
     * Checks is the given ISBN number is a valid ISBN13 number.
     *
     * @param isbn13 the ISBN number to validate
     * @return true is the number if a valid ISBN number, false otherwise
     */
    public boolean validate(String isbn13) {
        //Checks if inputted string is a number or not
        try {
            long checkLong = Long.parseLong(isbn13);

        } catch (NumberFormatException e) {
            return false;
        }
        //Checks if the number is 13 digits long
        if(isbn13.length() != 13) {
            return false;
        }
        //Stores the check-digit, to check at the end
        int checkDigit = Integer.parseInt(isbn13.substring(isbn13.length() - 1));

        int sum = 0;
        int i = 0;
        //Iterates through the first 12 digits, and adds then, multiplying by 1 or 3 based on their position
        while(i < 12) {
            int currentDigit = Character.getNumericValue(isbn13.charAt(i));
            if(i % 2 == 1) {
                sum += currentDigit * 3;
            } else {
                sum += currentDigit;
            }
            i++;
        }
        sum = sum % 10;
        if(checkDigit == 0) {
            return sum == 0;
        } else {
            return checkDigit == 10 - sum;
        }
    }
}
