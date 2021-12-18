def find_directory(directory)
    Dir.foreach(directory) do |filename|
        if File.directory?("#{directory}/#{filename}") &&
            filename != "." && filename != ".."
            puts "#{directory}/#{filename}"
        end
    end
end


def recursive_find(directory)
    
    Dir.foreach(directory) do |filename|
       if File.directory?("#{directory}/#{filename}") && 
        filename != "." && filename != ".."
            puts "#{directory}/#{filename}"
           recursive_find("#{directory}/#{filename}")
       end
    end
end


# find_directory("/home/sam")


recursive_find("/home/sam")
