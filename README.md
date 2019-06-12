# MovieAPI


1. Las capas de la aplicación (por ejemplo capa de persistencia, vistas, red, negocio, etc) y qué clases pertenecen a cual.

R= Las capas de la aplicación son:

- Vistas: en esta capa se encuentran aquellos elementos relacionados con "lo que se le muestra al usuario" como por ejemplo: las vistas definidas en los archivos layouts. Todos los archivos layouts pertenecen a esta capa.

- Negocio o Aplicativa: en donde se encuentra la lógica de nuestra aplicación y que nos permite interactuar con la Vista. Las clases que pertenecen a esta capa son: MainActivity.kt, todas las clases que se encuentran dentro del directorio "fragment" y todas las clases que se encuentran en el directorio "adapter".

- Modelo: básicamente es la encargada de acceder a los datos u obtenerlos para ser enviandos a la capa de negocio. Las clases que pertenecen a esta capa son: aquellas clases definidas dentro del directorio "model" y las creadas dentro del directorio "client" que tiene una clase (Request.kt) y una interface (API.kt)

2. La responsabilidad de cada clase creada.

R= A continuación la calsificación de las clases con su responsabilidad:

- MainActivity: representar y manejar el activity principal de la aplicación en donde se mostrarán los fragments que se manejan en la aplicación.

- HomeFragment: representar y manejar el fragment que contiene un TabLayout y un ViewPager para mostrar las listas de Peliculas o Series en las diferentes pestañas del TabLayout.

- MoviePopularFragment, TvPopularFragment: representar y manejar el listado de Peliculas y Series respectivamente Popular.

- MovieTopRatedFragment, TvTopRatedFragment: representar y manejar el listado de Peliculas y Series respectivamente Top Rated.

- MovieUpcomingFragment, TvUpcomingFragment: representar y manejar el listado de Peliculas y Series respectivamente Upcoming.

- MovieDiscoverFragment, TvDiscoverFragment: representar y manejar el listado de Peliculas y Series respectivamente buscadas por categorías.

- MovieDetailFragment, TvDetailFragment: representar y manejar el detalle de Peliculas y Series respectivamente.

- MoviesAdapter, TvAdapter: representar y manejar el adaptador del listado de Peliculas y Series respectivamente (gestiona cada item de los listados).

- MoviesViewPagerAdapter, TvViewPagerAdapter: representar y manejar el adaptador del ViewPager para los TabLayout de Peliculas y Series respectivamente (gestiona cada viewPager).

- API: interface con la estructura de las peticiones REST a realizar apoyandose de la libreria Retrofit, como por ejemplo: peticiones GET con sus parametros.

- Request: permite establecer los valores y objetos para realizar la conexión con la API para poder realizar las peticiones definidas en la interface API.

- Clases en el directorio "model": representan los modelos o estructura de datos en las que almacenarán o recibirán dichos datos de las peticiones realizadas a la API.

- Archivos layouts: representan las vistas o interfaces de usuario con las que interactua el mismo.




1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?

R= Es un principio que indica básicamente que un objeto o clase debe realizar una única cosa o cumplir una única función. El propósito de este principio es establecer una especie de "regla" para crear software de calidad, definiendo una función específica para cada clase y objeto creado.

2. Qué características tiene, según su opinión, un “buen” código o código limpio?

R= 
- Simplicidad: el código es lo mas simple posible para poder ser entendible por el desarrollador que lo escribió o por otro.
- Fácil aprendizaje: el código es más fácil de entender y de aprender por personas externas al desarrolador debido a su simplicidad (mencionado en el punto anterior).
- Mantenible: debido a los dos puntos anteriores permite de manera sencilla la reutilización de código e implementar actualizaciones.
- Detección de fallas: con un código limpio es mucho más sencillo y más rapido detectar fallas en el código y poder solucionarlas.
- Fácil testeo: permite realizar de manera más sencilla tests sobre la aplicación.
- Buenas prácticas: se tiene presente la implementación de "buenas prácticas", pruebas unitarias, patrones de diseños, etc.
