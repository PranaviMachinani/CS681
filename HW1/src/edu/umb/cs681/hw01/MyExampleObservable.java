package edu.umb.cs681.hw01;

public class MyExampleObservable {

		public static void main(String args[]) {

			DJIAQuoteObservable dQObs = new DJIAQuoteObservable();
			dQObs.addObserver((Observable o, Object obj) -> {
				System.out.println("DJIA Observer has been notified");
			});
			dQObs.changeQuote("ABC", 1);

			StockQuoteObservable sQObs = new StockQuoteObservable();
			sQObs.addObserver((Observable o, Object obj) -> {
				System.out.println("Stock Observer has been notified.");
			});
			sQObs.changeQuote("XYZ", 2);
		}
	}