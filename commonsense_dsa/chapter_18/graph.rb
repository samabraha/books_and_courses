class Vertex
    attr_accessor :value, :adjacent_vertices

    def initialize(value)
        @value = value
        @adjacent_vertices = []
    end

    def add_adjacent_vertices(vertex)
        @add_adjacent_vertices << vertex
    end
end

alice = Vertex.new("Alice")
bob = Vertex.new("Bob")
cynthia = Vertex.new("Cynthia")

alice.add_adjacent_vertices(bob)
alice.add_adjacent_vertices(cynthia)
bob.add_adjacent_vertices(cynthia)
cynthia.add_adjacent_vertices(bob)