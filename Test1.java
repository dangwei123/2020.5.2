 实现 int sqrt(int x) 函数。

计算并返回 x 的平方根，其中 x 是非负整数。

由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
class Solution {
    public int mySqrt(int x) {
        if(x==0) return 0;
        int n=x;
        while(n>x/n){
            n=(n+x/n)>>>1;
        }
        return n;
    }
}

UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：

对于 1 字节的字符，字节的第一位设为0，后面7位为这个符号的unicode码。
对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为0，后面字节的前两位一律设为10。剩下的没有提及的二进制位，全部为这个符号的unicode码。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/utf-8-validation
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public boolean validUtf8(int[] data) {
        int n=0;
        for(int i=0;i<data.length;i++){
            if(n>0){
                if(data[i]>>>6!=2) return false;
                n--;
            }else if(data[i]>>>7==0){
                n=0;
            }else if(data[i]>>>5==0b110){
                n=1;
            }else if(data[i]>>>4==0b1110){
                n=2;
            }else if(data[i]>>>3==0b11110){
                n=3;
            }else{
                return false;
            }
        }
        return n==0;
    }
}

编写一个 SQL 查询，获取 Employee 表中第二高的薪水（Salary） 。
# Write your MySQL query statement below
select (select distinct salary from Employee order by salary desc limit 1 offset 1 ) SecondHighestSalary;

给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for(int i=0;i<nums.length;i++){
            int t=target-nums[i];
            int index=map.getOrDefault(t,-1);
            if(index!=-1&&index!=i){
                return new int[]{i,index};
            }
        }
        return new int[]{-1,-1};
    }
}

给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m=nums1.length;
        int n=nums2.length;
        if(m>n){
            int[] tmp=nums1;
            nums1=nums2;
            nums2=tmp;
            
            int t=m;
            m=n;
            n=t;
        }
        int half=(m+n+1)/2;
        int left=0;
        int right=m;
        while(left<=right){
            int i=left+(right-left)/2;
            int j=half-i;
            if(i<m&&nums1[i]<nums2[j-1]){
                left=i+1;
            }else if(i>0&&nums1[i-1]>nums2[j]){
                right=i-1;
            }else{
                int maxright=0;
                if(i==0){
                    maxright=nums2[j-1];
                }else if(j==0){
                    maxright=nums1[i-1];
                }else{
                    maxright=Math.max(nums1[i-1],nums2[j-1]);
                }
                if((m+n)%2==1) return maxright;
                
                int minleft=0;
                if(i==m){
                    minleft=nums2[j];
                }else if(j==n){
                    minleft=nums1[i];
                }else{
                    minleft=Math.min(nums1[i],nums2[j]);
                }
                
                return (maxright+minleft)/2.0;
            }
        }
        return 0.0;
    }
}

给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
class Solution {
    public String longestPalindrome(String s) {
        int res=0;
        int begin=0;
        for(int i=0;i<s.length();i++){
            int a=extend(s,i,i);
            int b=extend(s,i,i+1);
            int c=Math.max(a,b);
            if(c>res){
                begin=i-(c-1)/2;
                res=c;
            }
        }
        return s.substring(begin,begin+res);
    }
    private int extend(String s,int left,int right){
        while(left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        return right-left-1;
    }
}

