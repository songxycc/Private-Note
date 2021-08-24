#### 查看目录下文件个数 包括子目录
```ls -lR | grep "^-" | wc -l ```
#### 查看目录下文件夹/文件大小 
```ls -lR | grep "^d"|wc -l   ### or ' du -h -d1 ' ```
#### 查看目录下文件夹个数
```ls -l | grep "^d"| wc -l```
##### *说明
```
- **ls -l**  长列表输出该目录下文件信息
- **grep "^-"**  这里将长列表输出信息过滤一部分，只保留一般文件，如果只保留目录就是 ^d
- **wc -l** 统计输出信息的行数，因为已经过滤得只剩一般文件了，所以统计结果就是一般文件信息的行数，又由于一行信息对应一个文件，所以也就是文件的个数。
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

#####