class Node
    attr_accessor :data, :next_node

    def initialize(data)
        @data = data
    end
end

class LinkedList
    attr_accessor :first_node

    def initialize(first_node)
        @first_node = first_node
    end
end

node_1 = Node.new("Luevenhoek")
node_2 = Node.new("Darwin")
node_3 = Node.new("Pasteur")
node_4 = Node.new("Watson")
node_5 = Node.new("Crick")

node_1.next_node = node_2
node_2.next_node = node_3
node_3.next_node = node_4
node_4.next_node = node_5

list = LinkedList.new(node_1)

p list