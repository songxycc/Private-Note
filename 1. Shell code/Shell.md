#### 查看目录下文件个数 包括子目录
```ls -lR | grep "^-" | wc -l ```
#### 查看目录下文件夹/文件大小 
```ls -lR | grep "^d"|wc -l   ### or ' du -h -d1 ' ```
#### 查看目录下文件夹个数
```ls -l | grep "^d"| wc -l```
##### *说明
```
- **ls -l**   长列表输出该目录下文件信息
- **grep "^-"**   将长列表输出信息过滤只保留一般文件，如果只保留目录就是 ^d
- **wc -l**   统计输出信息的行数，一行信息对应一个文件，所以也就是文件的个数。
```
#### 统计当前文件夹(目录)大小，并按文件大小排序
```du -sh * | sort -n```
#### 查看指定文件大小
```du -sk filename ```
<br>
<br>

### **AWK/GREP/SED**
##### 合并txt
```awk '{print $0}' file1  file2  > file3```
##### 比较异同
```awk 'NR==FNR{a[$5];next} $5 in a {print $0}' file2.txt file1.txt```

##### 打印行数
```cat filename | awk '{print NF}' > out.txt ```

##### 比对是否相同
```awk '{if($1!~/^1364$/)print}' int.txt > out.txt```

##### 随机抽取n行
```awk 'BEGIN{srand()} {print rand()"\t"$0}'  input_file | sort -nk 1 | head -n line_num | awk -F "\t" '{print $2}' ```

##### 改字符
```sed -e 's/SN:chr01/SN:1/'```
##### 求平均数
```cat test.txt | awk '{sum+=$0}END{print sum/NR}' ```

##### 删除字符
```sed '/LN:480980714/d'```

##### 删除特定行
```sed -e '/abc/d;/def/d'  a.txt ```
##### 追加文件
```cat chr${i}.fa >> hg19.fasta```

##### 删除空行
``` cat chr${i}.fa >> hg19.fasta```

##### 删除某几行
```sed -i '1,100d' file```

##### 更高特定字符
```
sed -i '3s/aa/ff/' file   --针对file文件中的第三行，将其中的aa替换为ff
sed -i '/xxx/s/aa/ff/g' file   --找出包含xxx的行，并将其中的aa替换为ff
sed -i '1s/[#*]/ff/gp' file  --针对文件第1行，将其中的#号或是*号替换为ff
```

##### if else
```awk -F '\t' '{if($8 ~ /AF=1.0/) print $0,DD"\t", "1/1";else print $0,"\t", "0/1"}' ```

##### 生成文件写入内容
```for i in {2..10}; do echo "sample${i}" > sample${i}.txt; done```

##### 新增一行
```awk '{print $0,"cwy"}' test1.txt > test2.txt```<br>
```awk '{print $1,$2,"cwy",$3}' test1.txt > test3.txt  ```（中间新增)
##### 新增列指定分隔符
``` awk 'BEGIN{FS=OFS="\t"}END{print $0,"sss"}' file ```