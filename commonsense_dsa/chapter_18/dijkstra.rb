class City
    attr_accessor :name, :routes

    def initialize(name)
        @name = name
        @routes = {}
    end

    def add_route(city, price)
        @routes[city] = price
    end
end

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

        current_city.routes.each do |adjacent_city, price|
            unvisited_cities << adjacent_city unless visited_cities[adjacent_city.name]

            price_through_current_city = cheapest_prices_table[current_city.name] + price

            if !cheapest_prices_table[adjacent_city.name] || 
                    price_through_current_city < cheapest_prices_table[adjacent_city.name]

                cheapest_prices_table[adjacent_city.name] = price_through_current_city
                cheapest_previous_stopover_city_table[adjacent_city.name] = current_city.name
            end
        end

        current_city = unvisited_cities.min do |city|
            cheapest_prices_table[city.name]
        end
    end

    shortest_path = []

    current_city_name = final_destination.name

    while current_city_name != starting_city.name
        shortest_path << current_city_name

        current_city_name = cheapest_previous_stopover_city_table[current_city_name]
    end

    shortest_path << starting_city.name

    return shortest_path.reverse
 end

asmara = City.new("asmara")
keren = City.new("keren")
mendefera = City.new("mendefera")
barentu = City.new("barentu")
massawa = City.new("massawa")
assab = City.new("assab")
adi_keyh = City.new("adi_keyh")
teseney = City.new("teseney")
dekemhare = City.new("dekemhare")
akordat = City.new("akordat")
adikuala = City.new("adikuala")
gindae = City.new("gindae")

asmara.add_route(keren, 91)
asmara.add_route(mendefera, 54)
# asmara.add_route(adi_keyh, 114)
keren.add_route(barentu, 67)
# mendefera.add_route(adikuala, 80)
# barentu.add_route(akordat, 60)
# massawa.add_route(asmara, 115)
# assab.add_route(massawa, 800)
# adi_keyh.add_route(mendefera, 80)
# teseney.add_route(barentu, 100)
# dekemhare.add_route(gindae, 50)
akordat.add_route(teseney, 50)
 
 puts dijkstra_shortest_path(asmara, teseney)
