# ioss-registration-journey-tests
UI test suite for the `ioss-registration-frontend` using WebDriver and `<scalatest/cucumber>`.  

## Running the tests

Prior to executing the tests ensure you have:
 - Docker - to run mongo and browser (Chrome, Firefox or Edge) inside a container - see guide here - https://docs.tax.service.gov.uk/mdtp-handbook/documentation/developer-set-up/install-docker.html
 - Installed/configured [service manager](https://github.com/hmrc/service-manager).  
 - Selenium Grid - see section further down

Run the following commands to start mongo and services locally:

    Run mongo at 4.4 with a replica set:
    docker run --restart unless-stopped -d -p 27017-27019:27017-27019 --name mongo4 mongo:4.4 --replSet rs0
    
    Connect to said replica set:
    docker exec -it mongo4 mongo

    When that console is there:
    rs.initiate()

    Start services via service manager:
    sm2 --start IMPORT_ONE_STOP_SHOP_ALL 

Then execute the `run_tests.sh` script:

    ./run_tests.sh <browser-driver> <environment> 

The `run_tests.sh` script defaults to using `chrome` in the `local` environment.  For a complete list of supported param values, see:
 - `src/test/resources/application.conf` for **environment**

## Selenium Grid

There are 2 ways to run this

### Local
This will run similarly to the previous experience. Probably will be preferred for those who like fast feedback as it easily works 
from running cucumber tests in intellij and doing the following loop. Run all tests, pin results which allows us to not lose sight of our
failures, then work on any failures one by one easily.

Put a Thread.sleep() to pause at a point and then just CTRL-R (Mac)/Shift-f10 to rerun it without
having to go back to the feature file. You can also pause prior to the point of failure in a step definition, 
so you can debug what is happening manually without having to go through convoluted (potentially errant from clumsiness) clicking
journeys to get to that point.

https://github.com/hmrc/local-selenium-grid

#### Chrome version
This is set in 

```
browser-versions.toml
```

under 

```toml
[[node.driver-configuration]]
display-name = "Chrome"
stereotype = '{"browserName": "chrome", "browserVersion": "120"}'
```

This will need to change to the version you are running and have the relevant version of the chromedriver installed.

https://googlechromelabs.github.io/chrome-for-testing/

#### Chromedriver on OSX 
On the previous webdriver we only had to click allow once, but it now complains about being unsigned on each browser opening.
This can be solved by 

(maybe need sudo)
```
xattr -d com.apple.quarantine  /path/to/chromedriver
```

##### Starting
Simply run
```
./start.sh
```

#### Running Cucumber in Intellij
We need to pass the following VM options across

```
-Dbrowser=chrome -Denvironment=local
```

Goto 
```
Run->Edit Configuration Templates (bottom left)->Cucumber Java->VM Options
```

The values here get copied across to all NEW run configurations. 


### Non-Local
You will need to run Selenium Grid via the instructions here - https://github.com/hmrc/docker-selenium-grid

## ZAP and Accessibility tests

ZAP and Accessibility tests are bundled together with the journey tests on Jenkins. 

The reports for these can be accessed via the journey test build here - https://build.tax.service.gov.uk/job/One%20Stop%20Shop/job/ioss-registration-journey-tests/

## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").