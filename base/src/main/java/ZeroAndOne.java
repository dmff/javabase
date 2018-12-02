/**
 * @author dmf
 * @date 2018/6/21
 */
public class ZeroAndOne {

    public static int DP(int[] val, int[] wei, int m) {
        int len = val.length;
        //开辟大小为len+1,m+1大小的空间
        int[][] V = new int[len + 1][m + 1];
        //前0个物品，容量为多少价值都是0
        for (int i = 0; i < m; i++) {
            V[0][i] = 0;
        }
        //容量为0，前多少个物品的价值都是0
        for (int i = 0; i < len; i++) {
            V[i][0] = 0;
        }
        for( int weight = 1; weight <= m; weight++){
            for (int n = 1; n <= len; n++) {
                //当前的物品的容量小于等于剩余容量，则判断加入当前物品后总容量与未加入容量的大小比较
                if (wei[n - 1] <= weight) {
                    V[n][weight] = Math.max(val[n - 1] + V[n - 1][weight - wei[n - 1]], V[n - 1][weight]);
                } else {
                    //如果当前物品的容量大于剩余容量，则一定不能加入该物品，则最大价值与前n-1个的最大价值相同
                    V[n][weight] = V[n - 1][weight];
                }
            }
        } for( int i[]:V){
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        return V[ len][m];
    }

    public static void main(String[] args) {
        int Weight[] = {2, 3, 1, 4, 6, 5};
        int Value[] = {5, 6, 5, 1, 19, 7};
        int nCapacity = 10;
        System.out.println(DP(Value, Weight, nCapacity));
    }
}
