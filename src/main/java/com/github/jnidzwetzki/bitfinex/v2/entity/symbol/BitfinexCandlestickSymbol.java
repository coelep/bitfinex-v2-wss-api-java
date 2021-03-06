/*******************************************************************************
 *
 *    Copyright (C) 2015-2018 Jan Kristof Nidzwetzki
 *  
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License. 
 *    
 *******************************************************************************/
package com.github.jnidzwetzki.bitfinex.v2.entity.symbol;

import com.github.jnidzwetzki.bitfinex.v2.entity.Timeframe;

public class BitfinexCandlestickSymbol implements BitfinexStreamSymbol {
	
	/**
	 * The symbol
	 */
	private final BitfinexCurrencyPair symbol;
	
	/**
	 * The timeframe
	 */
	private final Timeframe timeframe;

	public BitfinexCandlestickSymbol(final BitfinexCurrencyPair symbol, final Timeframe timeframe) {
		this.symbol = symbol;
		this.timeframe = timeframe;
	}

	public BitfinexCurrencyPair getSymbol() {
		return symbol;
	}

	public Timeframe getTimeframe() {
		return timeframe;
	}
	
	/**
	 * To Bitfinex symbol string
	 * @return
	 */
	public String toBifinexCandlestickString() {
		return "trade:" + timeframe.getBitfinexString() + ":" + symbol.toBitfinexString();
	}
	
	/**
	 * Construct from Bitfinex string
	 * @param symbol
	 * @return
	 */
	public static BitfinexCandlestickSymbol fromBitfinexString(final String symbol) {
		
		if(! symbol.startsWith("trade:")) {
			throw new IllegalArgumentException("Unable to parse: " + symbol);
		}
		
		final String[] splitString = symbol.split(":");

		if(splitString.length != 3) {
			throw new IllegalArgumentException("Unable to parse: " + symbol);
		}
		
		final String timeframeString = splitString[1];
		final String symbolString = splitString[2];
		
		return new BitfinexCandlestickSymbol(
				BitfinexCurrencyPair.fromSymbolString(symbolString), 
				Timeframe.fromSymbolString(timeframeString));
	}

	@Override
	public String toString() {
		return "BitfinexCandlestickSymbol [symbol=" + symbol + ", timeframe=" + timeframe + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((timeframe == null) ? 0 : timeframe.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BitfinexCandlestickSymbol other = (BitfinexCandlestickSymbol) obj;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (timeframe != other.timeframe)
			return false;
		return true;
	}
	
	
}
