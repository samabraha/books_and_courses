class TreeNode:
    def __init__(self, val, left=None, right=None):
        self.value = val
        self.leftChild = left
        self.rightChild = right
    
    def search(searchValue, node):
        if node is None or node.value == searchValue:
            return node
        elif searchValue < node.value:
            return search(searchValue, node.leftChild)
        else:
            return search(searchValue, node.rightChild)

    def insert(value, node):
        if value < node.value:
            if node.leftChild is None:
                node.leftChild = TreeNode(value)
            else:
                insert(value, node.leftChild)
        elif value > node.value:
            if node.rightChild is None:
                node.rightChild = TreeNode(value)
            else:
                insert(value, node.rightChild)

    def delete(valueToDelete, node):
        if node is None:
            return None
        
        elif valueToDelete < node.value:
            node.leftChild = delete(valueToDelete, node.leftChild)
            return node
        
        elif valueToDelete > node.value:
            node.rightChild = delete(valueToDelete, node.rightChild)
            return node

        elif valueToDelete == node.value:
            if node.leftChild is None:
                return node.rightChild
            
            elif node.rightChild is None:
                return node.leftChild
            
            else:
                node.rightChild = lift(node.rightChild, node)
                return node
    
    def lift(node, nodeToDelete):
        if node.leftChild:
            node.leftChild = lift(node.leftChild, nodeToDelete)
            return node
        else: 
            nodeToDelete.value = node.value
            return node.rightChild
        
    def traverse_and_print(node):
        if node is None:
            return

        TreeNode.traverse_and_print(node.leftChild)
        print(node.value)
        TreeNode.traverse_and_print(node.rightChild)
    
    def max_val(node):
        if node.rightChild:
            return TreeNode.max_val(node.rightChild)
        else:
            return node.value

        
node1 = TreeNode(25)
node2 = TreeNode(75)

root = TreeNode(50, node1, node2)

TreeNode.traverse_and_print(root)
print(TreeNode.max_val(root))
