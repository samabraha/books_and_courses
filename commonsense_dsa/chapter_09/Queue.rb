class Queue
    def initialize
        @data = []
    end

    def queue(element)
        @data << element
    end

    def dequeue
        @data.shift
    end

    def read
        @data.first
    end
end

class PrintManager
    def initialize
        @queue = Queue.new
    end

    def queue_print_job(document)
        @queue.queue(document)
    end

    def run
        while @queue.read
            print(@queue.dequeue)
        end
    end

    private

    def print(document)
        puts document
    end
end

print_manager = PrintManager.new


print_manager.queue_print_job("2 documet")
print_manager.queue_print_job("4 documet")
print_manager.queue_print_job("1 documet")
print_manager.queue_print_job("5 documet")
print_manager.queue_print_job("10 documet")

print_manager.run
