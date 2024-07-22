# ioss-registration-journey-tests
UI test suite for the `ioss-registration-frontend` using WebDriver and `<scalatest/cucumber>`.  

## Running the tests

Prior to executing the tests ensure you have:
 - Docker - to run mongo inside a container - see guide here - https://docs.tax.service.gov.uk/mdtp-handbook/documentation/developer-set-up/install-docker.html
 - Installed/configured [service manager](https://github.com/hmrc/service-manager).

Run the following commands to start mongo and services locally:

    Run mongo at 4.4 with a replica set:
    docker run --restart unless-stopped -d -p 27017-27019:27017-27019 --name mongo4 mongo:4.4 --replSet rs0
    
    Connect to said replica set:
    docker exec -it mongo4 mongo

    When that console is there:
    rs.initiate()

    Start services via service manager:
    sm2 --start IMPORT_ONE_STOP_SHOP_ALL 

Then execute the relevant run tests script - these have been split up as the functionality has grown across the service:

    ./run_registration_tests.sh <browser-driver> <environment> 
    ./run_amend_rejoin_tests.sh <browser-driver> <environment> 
    ./run_change_save_tests.sh <browser-driver> <environment> 

Can also use "./run_wip.sh <browser-driver> <environment>" to run individual scenarios tagged with @wip

The run test scripts default to using `chrome` in the `local` environment.  For a complete list of supported param values, see:
 - `src/test/resources/application.conf` for **environment**

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