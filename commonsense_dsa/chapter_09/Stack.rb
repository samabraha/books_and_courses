class Stack
    def initialize
        @data = []
    end
    
    def push(element)
        @data << element
    end

    def pop
        @data.pop
    end

    def read
        @data.last
    end
end

class Linter
    def initialize
        @stack = Stack.new
    end

    def lint(text)
        text.each_char do |char|
            if is_opening_brace?(char)
                @stack.push(char)
            elsif is_closing_brace?(char)
                popped_opening_brace = @stack.pop
                if !popped_opening_brace
                    return "#{char} doesn't have matching opening brace."
                end

                if is_not_a_match(popped_opening_brace, char)
                    return "#{} has mismatched opening brace."
                end
            end
        end

        if @stack.read
            return "#{@stack.read} does not have closing brace"
        end

        return true
    end

    private

    def is_opening_brace?(char)
        ["(", "{", "["].include?(char)
    end

    def is_closing_brace?(char)
        [")", "}", "]"].include?(char)
    end

    def is_not_a_match(opening_brace, closeing_brace)
        closeing_brace != {"(" => ")", "{" => "}", "[" => "]"}[opening_brace]
    end
end

myLinter = Linter.new

puts(myLinter.lint("class (s,s)(d, dd, ddd) []}"))