### **SamTools**
***
##### 提取区域bam
```samtools view -b input.bam 1:85000000-95000000 -o ouput.bam  ```
##### sort
```samtools sort -@ 32 -O bam -o output.bam input.bam```
##### 按染色体拆分bam
```samtools view -b -h intput.bam 1 > output.bam ```
##### 修改染色体字符
```samtools view -H $1 | sed -e 's/SN:\([0-9XY]\)/SN:chr\1/' -e 's/SN:MT/SN:chrM/' | samtools reheader - $1 > $2```

### **sra转fq**
```prefetch --option-file sra.txt```  # 下载sra <br>
```pfastq-dump SRR2090164.sra --split-3 --gzip -O outfile_path -t 8 ``` 

### **Plink**
***
##### 转换plink格式
```plink --vcf input.vcf --autosome-num 42 --recode --out output --double-id```
##### 查看缺失率
```plink --file input --autosome-num 42 -missing```
##### 查看maf
```plink --file output --autosome-num 42 --freq --out MAF_check```
##### 查看杂合度
```plink --file output --het --out R_check```

### **BWA**
***
##### mem比对
```bwa mem -t 4 -R '@RG\tID:xxx\tPL:illumina\tSM:xxx' ref.fa reads1.fq.gz reads2.fq.gz| samtools view -Sb - > out.bam```
<br>
*参考基因组  ```bwa index file.fa ``` 建立索引

### **Picard**
***
##### 去重
```java -jar picard.jar MarkDuplicates REMOVE_DUPLICATES=true I=input.bam O=output.bam M=mdMetrics.txt ```
##### 生成dict
```picard CreateSequenceDictionary R=chr1.fasta.gz O=chr1.dict ```
##### 加header
```
picard AddOrReplaceReadGroups -I XX.bam -O xx_header.bam --RGID human --RGLB lib1 --RGPL illumina  --RGPU unit1  --RGSM human  --VALIDATION_STRINGENCY LENIENT  -Xms1g -Xmx10g -XX:ParallelGCThreads=4
@RG\tID:BT01418\tPL:BGIseq500\tPU:190301_I17_V300016285_L1_WHEzsvRAABU-509\tLB:WHEzsvRAABU-509\tSM:BT01418
```

### **VCFtools/BCFtools**
***
##### 过滤
```
--remove-indels             
--remove-filtered-all 
--max-missing 
--recode
--recode-INFO-all 
```
##### 比较vcf files
```vcftools --vcf input.vcf --diff diff.vcf --diff-site --out 1_vs_2```
##### 抽取vcf
```bcftools view -S samplelistname.txt input.vcf.gz -Ov > output.vcf```
##### merge vcf files from different samples
```
bgzip file1.vcf 
tabix -p vcf file1.vcf.gz
bcftools concat file1.vcf.gz file2.vcf.gz 
```
##### merge vcf files from  the same sample
```bcftools merge *vcf.gz -Oz -o Merged.vcf.gz```

### **GATK**
***
##### SNP calling
```
gatk HaplotypeCaller -R /hg38.fa -I intput.bam -ERC GVCF -O output.g.vcf 

find -name "*.g.vcf" > input.list

gatk  CombineGVCFs -R /data1/home/xinyue/ref/ABD/abd_iwgscV1.fa.gz --variant input.list -O combined.g.vcf.gz 

gatk GenotypeGVCFs -R /data1/home/xinyue/ref/ABD/abd_iwgscV1.fa.gz -V combined.g.vcf.gz -G StandardAnnotation  -O raw_variants.vcf
```

### **dwgsim** 
***
##### simulate  datasets
```dwgsim -e 0.02 -E 0.02 -d 350 -1 150 -2 150 -r 0.001 -R 0.5 -N 39000000 -c 0 ref.fa test```

##### simulation ROC
``` 
rtg format -o hg19.sdf ucsc.hg19.fasta
rtg vcfeval -b high-confident.vcf.gz -c input.vcf.gz -o output/ -t input.sdf
rtg rocplot weighted_roc.tsv.gz --svg hgoo1.svg  # draw ROC curve 
```