#### R画缺失率
```
indmiss<-read.table(file="plink.imiss", header=TRUE)
snpmiss<-read.table(file="plink.lmiss", header=TRUE)
pdf("imiss.pdf")
hist(indmiss[,6],main="Histogram individual missingness")
pdf("lmiss.pdf")
hist(snpmiss[,5],main="Histogram SNP missingness")
dev.off()
```

#### R画maf
```
maf_freq <- read.table("MAF_check.frq", header =TRUE, as.is=T)
pdf("MAF_distribution.pdf")
hist(maf_freq[,5],main = "MAF distribution", xlab = "MAF")
dev.off()
```

#### R画杂合度
```
het <- read.table("R_check.het", head=TRUE)
pdf("heterozygosity.pdf")
het$HET_RATE = (het$"N.NM." - het$"O.HOM.")/het$"N.NM."
hist(het$HET_RATE, xlab="Heterozygosity Rate", ylab="Frequency", main= "Heterozygosity Rate")
dev.off()
```

#### 运行R脚本
```Rscript --no-save shell.R```