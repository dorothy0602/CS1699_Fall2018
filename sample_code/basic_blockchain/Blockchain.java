
public class Blockchain {

    private HashPointer _head = null;

    public void addBlock(String data) {
	Block b = null;
	if (_head == null) {
	    b = new Block(null, data);
	} else {
	    b = new Block((Block) _head.getReference(), data);
	}

	_head = new HashPointer(b);
    }

    public boolean iterateAndVerify() {
	// Trivial case - no blocks in blockchain, it's valid
	if (_head == null) {
	    return true;
	}
	// Otherwise, iterate through all of the blocks until you get to null
	// If hashes don't match up, blockchain is invalid
	Block _current = (Block) _head.getReference();
	try {
	    while (_current != null) {
		System.out.println("Block data: " + _current.getDataAsString());
		_current = _current.previousBlock();

	    }
	} catch (InvalidHashException ihex) {
	    System.out.println("Invalid hash on block containing data:");
	    System.out.println(_current.getDataAsString());
	    return false;
	}
	// We have iterated through the entire blockchain without an error, thus
	// it is valid.
	return true;
    }



}