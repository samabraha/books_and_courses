class City
    attr_accessor :name, :routes

    def initialize(name)
        @name = name
        @route = {}
    end

    def add_route(City, price)
        @route[City] = price
    end
end

 atlanta = City.new("Atlanta")
 boston = City.new("Boston")
 chicago = City.new("Chicago")
 denver = City.new("Denver")
 el_paso = City.new("El Paso")
 
 atlanta.add_route(boston, 100)
 atlanta.add_route(denver, 0)
 boston.add_route(chicago, 0)
 boston.add_route(denver, 0)
 chicago.add_route(el_paso, 0)
 denver.add_route(chicago, 0)
 denver.add_route(el_paso, 0)
 
 
 def dijkstra_shortest_path(starting_city, final_destination)
    cheapest_prices_table = {}
    cheapest_previous_stopover_city_table = {}

    unvisited_cities = []

    visited_cities = {}

    cheapest_prices_table[starting_city.name] = 0

    current_city = starting_city

    while  current_city
        visited_cities[current_city.name] = true
        unvisited_cities.delete(current_city)

        
    end

 end