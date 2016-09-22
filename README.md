# unit-test-name-checker
Maven Plugin to validate test method names

Validate test methods annotated with @org.junit.Test conform to standard
**methodName_givenState_expectedResult**

**Usage:**

Build and add plugin to your repo with:
mvn clean install

Then add plugin to your projects pom.xml
```xml
            <plugin>
                <groupId>com.github.eric-mckinley</groupId>
                <artifactId>unit-test-name-checker</artifactId>
                <version>1.1.0</version>
                <configuration>
                    <testClassNameSuffix>Test</testClassNameSuffix>
                    <consoleOutputs>
                        <consoleOutput>console</consoleOutput>
                        <consoleOutput>xml</consoleOutput>
                    </consoleOutputs>
                    <validators>
                        <validator>camel-case</validator>
                        <validator>three-segments</validator>
                        <validator>illegal-prefix</validator>
                        <validator>match-method</validator>
                    </validators>
                </configuration>

                <executions>
                    <execution>
                        <phase>test-compile</phase>
                        <goals>
                            <goal>methods</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```

Use command **mvn site** to generate html report.
Console report runs with test compile.

### Configuration Options


| Optionn        | Required           | Default  | Allowed | Description |
| -------------- | ------------------ | -------- | ------- | ----------- |
| scanPackage      | No |  | any value |Specify base package to inspect |
| consoleOutputs      | No |  | xml, console | Optional log output to console |
| validators      | Yes |  | camel-case, three-segments, illegal-prefix, match-method, all | Specify at least one of the validators to use |
| testClassNamePrefix | No | | | Prefix used by Test Class compared to the Class  in src/main/java |
| testClassNameSuffix | No | | | Suffix used by Test Class compared to the Class  in src/main/java |
| testReportName | No | test-methods | any value | The file name of the output html report |
### Sample html report
[Sample Report](report-html.png)

### Sample console output form mvn command line
```
[INFO] --------------------------------------------------------------------------------
[INFO] CHECK UNIT TEST METHOD NAMES - jcsvdao-examples - org.jcsvdao.examples.example01.Example01Test
[INFO] --------------------------------------------------------------------------------
[INFO] Method find_byAgeOver21_threeEntries passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_byGenderWoman_twoEntries passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_allEntries_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_byEmail_oneEntry passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_byMalePremium_twoEntries passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Summary -> 0% of class methods fully compliant, 0/5
[INFO]
[INFO] --------------------------------------------------------------------------------
[INFO] CHECK UNIT TEST METHOD NAMES - jcsvdao-examples - org.jcsvdao.examples.example02.Example02Test
[INFO] --------------------------------------------------------------------------------
[INFO] Method find_companyByPK_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_allAddresses_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_allCompanies_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Summary -> 0% of class methods fully compliant, 0/3
[INFO]
[INFO] --------------------------------------------------------------------------------
[INFO] CHECK UNIT TEST METHOD NAMES - jcsvdao-examples - org.jcsvdao.examples.example03.Example03Test
[INFO] --------------------------------------------------------------------------------
[INFO] Method find_allStudents_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_studentByPK_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_allPostgradStudents_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Summary -> 0% of class methods fully compliant, 0/3
[INFO]
[INFO] --------------------------------------------------------------------------------
[INFO] CHECK UNIT TEST METHOD NAMES - jcsvdao-examples - org.jcsvdao.examples.example04.Example04Test
[INFO] --------------------------------------------------------------------------------
[INFO] Method find_restaurantHasChef_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_allChefs_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_chefHasRestaurant_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_allRestaurants_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Summary -> 0% of class methods fully compliant, 0/4
[INFO]
[INFO] --------------------------------------------------------------------------------
[INFO] CHECK UNIT TEST METHOD NAMES - jcsvdao-examples - org.jcsvdao.examples.example05.Example05ATest
[INFO] --------------------------------------------------------------------------------
[INFO] Method find_allTeams_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_teamHasMembers_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_playerHasTeam_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_allPlayers_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Summary -> 0% of class methods fully compliant, 0/4
[INFO]
[INFO] --------------------------------------------------------------------------------
[INFO] CHECK UNIT TEST METHOD NAMES - jcsvdao-examples - org.jcsvdao.examples.example05.Example05BTest
[INFO] --------------------------------------------------------------------------------
[INFO] Method find_allTeams_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_teamHasMembers_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_playerHasTeam_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Method find_allPlayers_noErrors passed 75%, 3/4
[INFO] Passed -> Test Method segments are camel case
[INFO] Passed -> Test Method name format is part1_part2_part3
[INFO] Passed -> Test Method does not start with invalid prefix
[INFO] Failed -> Test Method name matches method in src/main/java
[INFO]
[INFO] Summary -> 0% of class methods fully compliant, 0/4
[INFO]
[INFO] --------------------------------------------------------------------------------
[INFO] --------------------------------------------------------------------------------
[INFO] Final report -> 0% of test classes fully compliant, 0/6
[INFO] --------------------------------------------------------------------------------
[INFO] --------------------------------------------------------------------------------
```

### Sample console XML output form mvn command line
```xml
<reports>
	<report>
		<className>org.jcsvdao.examples.example01.Example01Test</className>
		<testMethods>
			<testMethod>
				<name>find_byAgeOver21_threeEntries</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_byGenderWoman_twoEntries</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_allEntries_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_byEmail_oneEntry</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_byMalePremium_twoEntries</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<results>
				<methods>
					<passed>15</passed>
					<failed>5</failed>
					<total>20</total>
				</methods>
				<classes>
					<passed>0</passed>
					<failed>5</failed>
					<total>5</total>
				</classes>
				<percentage>0</percentage>
			</results>
		</testMethods>
	</report>
	<report>
		<className>org.jcsvdao.examples.example02.Example02Test</className>
		<testMethods>
			<testMethod>
				<name>find_companyByPK_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_allAddresses_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_allCompanies_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<results>
				<methods>
					<passed>9</passed>
					<failed>3</failed>
					<total>12</total>
				</methods>
				<classes>
					<passed>0</passed>
					<failed>3</failed>
					<total>3</total>
				</classes>
				<percentage>0</percentage>
			</results>
		</testMethods>
	</report>
	<report>
		<className>org.jcsvdao.examples.example03.Example03Test</className>
		<testMethods>
			<testMethod>
				<name>find_allStudents_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_studentByPK_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_allPostgradStudents_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<results>
				<methods>
					<passed>9</passed>
					<failed>3</failed>
					<total>12</total>
				</methods>
				<classes>
					<passed>0</passed>
					<failed>3</failed>
					<total>3</total>
				</classes>
				<percentage>0</percentage>
			</results>
		</testMethods>
	</report>
	<report>
		<className>org.jcsvdao.examples.example04.Example04Test</className>
		<testMethods>
			<testMethod>
				<name>find_restaurantHasChef_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_allChefs_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_chefHasRestaurant_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_allRestaurants_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<results>
				<methods>
					<passed>12</passed>
					<failed>4</failed>
					<total>16</total>
				</methods>
				<classes>
					<passed>0</passed>
					<failed>4</failed>
					<total>4</total>
				</classes>
				<percentage>0</percentage>
			</results>
		</testMethods>
	</report>
	<report>
		<className>org.jcsvdao.examples.example05.Example05ATest</className>
		<testMethods>
			<testMethod>
				<name>find_allTeams_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_teamHasMembers_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_playerHasTeam_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_allPlayers_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<results>
				<methods>
					<passed>12</passed>
					<failed>4</failed>
					<total>16</total>
				</methods>
				<classes>
					<passed>0</passed>
					<failed>4</failed>
					<total>4</total>
				</classes>
				<percentage>0</percentage>
			</results>
		</testMethods>
	</report>
	<report>
		<className>org.jcsvdao.examples.example05.Example05BTest</className>
		<testMethods>
			<testMethod>
				<name>find_allTeams_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_teamHasMembers_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_playerHasTeam_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<testMethod>
				<name>find_allPlayers_noErrors</name>
				<passed>
					<value>Test Method segments are camel case</value>
					<value>Test Method name format is part1_part2_part3</value>
					<value>Test Method does not start with invalid prefix</value>
				</passed>
				<failed>
					<value>Test Method name matches method in src/main/java</value>
				</failed>
				<result>
					<passed>3</passed>
					<failed>1</failed>
					<total>4</total>
				</result>
			</testMethod>
			<results>
				<methods>
					<passed>12</passed>
					<failed>4</failed>
					<total>16</total>
				</methods>
				<classes>
					<passed>0</passed>
					<failed>4</failed>
					<total>4</total>
				</classes>
				<percentage>0</percentage>
			</results>
		</testMethods>
	</report>
</reports>
```
