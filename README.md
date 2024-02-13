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