clear
pssh -h host16.txt -O StrictHostKeyChecking=no -o . 'chmod +x ServerScript.sh'
pssh -h host16.txt -O StrictHostKeyChecking=no -o . 'chmod +x DHTPerfTest.sh'
pssh -h host16.txt -O StrictHostKeyChecking=no -o . 'sh ./ServerScript.sh'
exit 0
