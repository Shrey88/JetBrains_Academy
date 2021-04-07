class ArrayOperations {
    public static void reverseElements(int[][] twoDimArray) {
        // write your code here
        for (int i = 0; i < twoDimArray.length; i++) {
            for (int j = 0, k = twoDimArray[0].length - 1; j < twoDimArray[0].length / 2; j++, k--) {
                int temp = twoDimArray[i][j];
                twoDimArray[i][j] = twoDimArray[i][k];
                twoDimArray[i][k] = temp;
            }
        }
    }
}