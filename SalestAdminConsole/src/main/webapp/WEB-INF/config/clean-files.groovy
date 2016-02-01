println "[Groovy] : clean-files.groovy"
println "targetDir : " + targetDir

if (fsh.test(targetDir)) {
   fsh.rmr(targetDir)
}