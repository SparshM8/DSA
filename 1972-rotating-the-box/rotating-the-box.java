class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m=boxGrid.length;
        int n=boxGrid[0].length;           
        char[][]ch=new char[n][m];
        for(char[]row:ch){
            Arrays.fill(row,'.');
        }
        for(int i=0;i<m;i++){
            int empty=n-1;
            for(int j=n-1;j>=0;j--){
               if(boxGrid[i][j]=='*'){
                ch[j][m-1-i]='*';
                empty=j-1;
               }else if(boxGrid[i][j]=='#'){
                ch[empty][m - 1 - i] = '#';
                 empty--;
               }
            }
        }
        return ch;
    }
}