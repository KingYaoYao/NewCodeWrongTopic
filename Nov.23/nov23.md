# 2020.11.23

```java
假设num已经被创建为一个ArrayList对象，并且最初包含以下整数值：[0，0，4，2，5，0，3，0]。 执行下面的方法numQuest(),最终的输出结果是什么？
private List<Integer> nums;

//precondition: nums.size() > 0
//nums contains Integer objects
public void numQuest() {
int k = 0;
Integer zero = new Integer(0);
while (k < nums.size()) {
if (nums.get(k).equals(zero))
nums.remove(k);
k++;
}
}
结果
    为：[0,4,2,5,3]   
```

size==8 ; k==0 , 因此第一次remove(0) ，删除索引为0的元素也就是第一个元素0，然后k++, size()--; 

​		此时集合元素为 ：[0，4，2，5，0，3，0] 

size==7; k==1，因此get(1)==4 !=0 , 不执行remove(); k++,因没有删除元素，size()不变， 

​		此时集合元素为：[0，4，2，5，0，3，0]

------

## i = i ++

```java
int b  = 0;
for(int i = 0; i <= 100;i++){
    b = b++;
}
b的值为？
    0
```

  Java中等价于：tmp = count；count++；count = tmp； 

  C++中等价于：tmp = count；count = tmp；count++；