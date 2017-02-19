clear

sudo apt-get update
sudo apt-get install default-jdk
sudo apt-get install ant

cd /home/ubuntu/TaskSchedulerLocal

java WorkloadGenerationUtil 100000 "sleep 0" Workload

ant jar

echo "======================================================="
echo "               Experiment for Throughput               "
echo "======================================================="
ant -Dt=1 -Dw=Workload run
ant -Dt=2 -Dw=Workload run
ant -Dt=4 -Dw=Workload run
ant -Dt=8 -Dw=Workload run
ant -Dt=16 -Dw=Workload run

echo "======================================================="
echo "               Experiment for Efficiency               "
echo "======================================================="

echo "-------------------------------------------------------"
echo "                Running for sleep 10ms                 "
echo "-------------------------------------------------------"
java WorkloadGenerationUtil 1000 "sleep 10" Workload
ant -Dt=1 -Dw=Workload run

java WorkloadGenerationUtil 2000 "sleep 10" Workload
ant -Dt=2 -Dw=Workload run

java WorkloadGenerationUtil 4000 "sleep 10" Workload
ant -Dt=4 -Dw=Workload run

java WorkloadGenerationUtil 8000 "sleep 10" Workload
ant -Dt=8 -Dw=Workload run

java WorkloadGenerationUtil 16000 "sleep 10" Workload
ant -Dt=16 -Dw=Workload run

echo "-------------------------------------------------------"
echo "                Running for sleep 1sec                 "
echo "-------------------------------------------------------"
java WorkloadGenerationUtil 100 "sleep 1000" Workload
ant -Dt=1 -Dw=Workload run

java WorkloadGenerationUtil 200 "sleep 1000" Workload
ant -Dt=2 -Dw=Workload run

java WorkloadGenerationUtil 400 "sleep 1000" Workload
ant -Dt=4 -Dw=Workload run

java WorkloadGenerationUtil 800 "sleep 1000" Workload
ant -Dt=8 -Dw=Workload run

java WorkloadGenerationUtil 1600 "sleep 1000" Workload
ant -Dt=16 -Dw=Workload run

echo "-------------------------------------------------------"
echo "               Running for sleep 10sec                 "
echo "-------------------------------------------------------"
java WorkloadGenerationUtil 10 "sleep 10000" Workload
ant -Dt=1 -Dw=Workload run

java WorkloadGenerationUtil 20 "sleep 10000" Workload
ant -Dt=2 -Dw=Workload run

java WorkloadGenerationUtil 40 "sleep 10000" Workload
ant -Dt=4 -Dw=Workload run

java WorkloadGenerationUtil 80 "sleep 10000" Workload
ant -Dt=8 -Dw=Workload run

java WorkloadGenerationUtil 160 "sleep 10000" Workload
ant -Dt=16 -Dw=Workload run


exit 0
