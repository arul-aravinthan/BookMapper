# Sets runTest as default target
default: runTests

# Compiles the .class file for AlgorithmEngineerTest
AlgorithmEngineerTest.class: AlgorithmEngineerTest.java
	javac AlgorithmEngineerTest.java

# Runs all Algorithm Engineer tests
runAlgorithmEngineerTests: AlgorithmEngineerTest.class
	java AlgorithmEngineerTest 

# Compiles the .class file for DataWranglerTest
DataWranglerTest.class: DataWranglerTest.java
	javac DataWranglerTest.java
# Runs all Data Wrangler tests
runDataWranglerTests: DataWranglerTest.class
	java DataWranglerTest

# Compiles the .class file for FrontendDeveloperTest
FrontendDeveloperTest.class: FrontendDeveloperTest.java
	javac FrontendDeveloperTest.java
# Runs all Frontend Developer tests
runFrontendDeveloperTests: FrontendDeveloperTest.class
	java FrontendDeveloperTest

# Compile the .class files for BackendDeveloperTest
BackendDeveloperTest.class: BackendDeveloperTest.java
	javac BackendDeveloperTest.java
# Run backend tests
runBackendDeveloperTests: BackendDeveloperTest.class
	java BackendDeveloperTest

# Compiles BookMapper; the main class
BookMapper.class: BookMapper.java
	javac BookMapper.java

# Runs tests for all 4 roles
runTests: runDataWranglerTests runBackendDeveloperTests runFrontendDeveloperTests runAlgorithmEngineerTests 

# Runs the frontend program
run: BookMapper.class
	java BookMapper

# Removes all .class files
clean:
	rm *.class
