package telran.time;

import java.time.temporal.Temporal;
import java.util.Iterator;

public class Friday13Range implements Iterable<Temporal> {
    Temporal from;
    Temporal to;
	@Override
	public Iterator<Temporal> iterator() {
		
		return new FridayIterator();
	}
	private class FridayIterator implements Iterator<Temporal> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Temporal next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}