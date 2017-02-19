clear
pssh -h host16.txt -O StrictHostKeyChecking=no -o . 'rm -rf SourceCode'
pssh -h host16.txt -O StrictHostKeyChecking=no -o . 'rm -rf DHTPerfTest.sh'
pssh -h host16.txt -O StrictHostKeyChecking=no -o . 'rm -rf ServerScript.sh'
exit 0
