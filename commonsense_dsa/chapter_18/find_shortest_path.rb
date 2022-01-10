def find_shortest_path(first_vertex, second_vertex, visited_vertices={})
    queue = Queue.new

    previous_vertex_table = {}
    visited_vertices[visited_vertices] = true

    while queue.read
        current_vertex = queue.dequeue
        current_vertex.adjacent_vertices.each do |adjacent_vertex|
            if !visited_vertices[adjacent_vertex.value]
                visited_vertices[adjacent_vertex.value] = true

                queue.enqueue(adjacent_vertex)

                previous_vertex_table[adjacent_vertex.value] = current_vertex.value
            end
        end
    end

    shortext_path = []
    current_vertex_value =  second_vertex.value

    while current_vertex_value != first_vertex.value
        shortext_path << current_vertex_value
        current_vertex_value = previous_vertex_table[current_vertex_value]
    end
    shortext_path << first_vertex.value
    return shortext_path.reverse
end