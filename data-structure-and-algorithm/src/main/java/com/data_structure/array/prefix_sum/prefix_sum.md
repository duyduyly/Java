## Prefix sum

- Step 1: create prefix sum Array and all element value equal 0
- step 2: set first value
- step 3: loop all and calculate base on element index (prefixSum.set(i, prefixSum.get(i - 1) + intArr[i]);)

```java
        int[] intArr = new int[]{1,2,3,4,5,6};
        int length = intArr.length;

        //create prefix sum Array with all element value equal 0
        List<Integer> prefixSum = Stream.generate(() -> 0).limit(length).collect(Collectors.toList());
        
        //set first value
        prefixSum.set(0, intArr[0]);

        //calculate sum base on index 
        //example index = 1 ==> [1,0,0,0,0,0]
        //example index = 2 ==> [1,3,0,0,0,0], for arr[1] = 2
        //example index = 3 ==> [1,3,6,0,0,0], for arr[1] = 3 and arr[2] = 3
        for (int i = 1; i < length; i++)
            prefixSum.set(i, prefixSum.get(i - 1) + intArr[i]);

        System.out.println(prefixSum);
```
```bash
[1, 3, 6, 10, 15, 21]
```

# Practice 1:
