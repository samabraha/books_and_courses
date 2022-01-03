# class Queue
#     def initialize
#         @data = []
#     end

#     def queue(element)
#         @data << element
#     end

#     def dequeue
#         @data.shift
#     end

#     def read
#         @data.first
#     end
# end

class Vertex
    attr_accessor :value, :adjacent_vertices

    def initialize(value)
        @value = value
        @adjacent_vertices = []
    end

    def add_adjacent_vertices(vertex)
        return if adjacent_vertices.include?(vertex)
        @adjacent_vertices << vertex
        vertex.add_adjacent_vertices(self)
    end
end

def dfs_traverse(vertex, visited_vertices={})
    visited_vertices[vertex.value] = true

    puts vertex.value

    vertex.adjacent_vertices.each do |adjacent_vertex|
        next if visited_vertices[adjacent_vertex.value]
        dfs_traverse(adjacent_vertex, visited_vertices)
    end
end

def dfs(vertex, search_value, visited_vertices={})
    return vertex if vertex.value == search_value

    visited_vertices[vertex.value] = true

    vertex.adjacent_vertices.each do |adjacent_vertex|
        next if visited_vertices[adjacent_vertex.value]
    
        return adjacent_vertex if adjacent_vertex.value == search_value

        wanted_vertex = dfs(adjacent_vertex, search_value, visited_vertices)
        return wanted_vertex if wanted_vertex
    end

    return nil
end

def bfs_traverse(starting_vertex)
    queue = []

    visited_vertices = {}

    visited_vertices[starting_vertex.value] = true

    queue << starting_vertex

    while queue.first
        current_vertex = queue.shift
        puts current_vertex.value

        current_vertex.adjacent_vertices.each do |adjacent_vertex|
            if !visited_vertices[adjacent_vertex.value]
                visited_vertices[adjacent_vertex.value] = true
                queue << adjacent_vertex
            end
        end
    end
end

boss = Vertex.new("Boss")
chief1 = Vertex.new("-Chief 1")
chief2 = Vertex.new("-Chief 2")
chief3 = Vertex.new("-Chief 3")

manager1 = Vertex.new("--Manager 1")
manager2 = Vertex.new("--Manager 2")
manager3 = Vertex.new("--Manager 3")

clerck1 = Vertex.new("---Clerck 1")
clerck2 = Vertex.new("---Clerck 2")
clerck3 = Vertex.new("---Clerck 3")

boss.add_adjacent_vertices(chief1)
boss.add_adjacent_vertices(chief2)
boss.add_adjacent_vertices(chief3)

chief1.add_adjacent_vertices(manager1)
chief2.add_adjacent_vertices(manager2)
chief3.add_adjacent_vertices(manager3)

manager1.add_adjacent_vertices(clerck1)
manager2.add_adjacent_vertices(clerck2)
manager3.add_adjacent_vertices(clerck3)

from = boss
puts "Depth-first-traversal of " + from.value
dfs_traverse(from)


puts "\nBreadth-first-traversal of " + from.value
bfs_traverse(from)
# puts dfs(alice, "Cynthia").value