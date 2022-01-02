class TrieNode:
    def __init__(self):
        self.children = {}
    
class Trie:
    def __init__(self):
        self.root = TrieNode()
    
    def search(self, word):
        currentNode = self.root

        for char in word:
            if currentNode.children.get(char):
                currentNode = currentNode.children[char]
            else:
                return None
        return currentNode
    
    def insert(self, word):
        currentNode = self.root

        for char in word:
            if currentNode.children.get(char):
                currentNode = currentNode.children[char]
            else:
                newNode = TrieNode()
                currentNode.children[char] = newNode
                currentNode = newNode
        currentNode.children["*"] = None
    
    def collect_all_words(self, node=None, word="", words=[]):
        currentNode = self.root
        for key, child_node in currentNode.children.items():
            if key == "*":
                words.append(word)
            else:
                self.collect_all_words(child_node, word + key, words)
        
        return words
    
    def autocomplete(self, prefix):
        current_node = self.search(prefix)
        if not current_node:
            return None
        return self.collect_all_words(current_node)
    
    def correct_user(self, prefix):
        correction_list = self.autocomplete(prefix)
        if len(correction_list) > 1:
            correction_list.sort(key=lambda word : len(word), referse=True)
            return correction_list[0]