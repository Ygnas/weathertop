# Ignas Baranauskas web assignment 2
# weathertop

This is a Web companion application for the device **WeatherTop 1000**, to displays collected readings separated by stations. 
Allowing user to view the readings, delete them or add new ones.

## Local Development setup

Navigate to project directory and run:

```sh
play idealize
```

Inside **application.conf** comment and uncomment shown lines.

```sh
application.mode=dev
#application.mode=prod

db.default=mem
#db=${SECRET}
```

## Deploy locally

Change **application.conf** as above

And run:

```sh
play run
```

## Production setup

Inside **application.conf** comment and uncomment shown lines.

```sh
#application.mode=dev
application.mode=prod

#db.default=mem
db=${SECRET}
```

Set up environment variable on your local machine or server

```sh
SECRET=yourPostgreSQLlink
```

## Contributing

1. Fork it (<https://github.com/Ygnas/weathertop>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request