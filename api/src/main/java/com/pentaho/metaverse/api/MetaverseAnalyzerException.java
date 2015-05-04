/*!
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright (c) 2002-2013 Pentaho Corporation..  All rights reserved.
 */

package com.pentaho.metaverse.api;

/**
 * The MetaverseAnalyzerException class represents an unexpected error that occurs during analysis.
 * 
 */
public class MetaverseAnalyzerException extends MetaverseException {

  /**
   * Default ID for serialization
   */
  private static final long serialVersionUID = -4755192809625931317L;

  /**
   * Instantiates a new default metaverse analyzer exception.
   */
  public MetaverseAnalyzerException() {
    super();
  }

  /**
   * Instantiates a new metaverse analyzerexception with the specified message.
   * 
   * @param message
   *          the message
   */
  public MetaverseAnalyzerException( String message ) {
    super( message );
  }

  /**
   * Instantiates a new metaverse exception from an existing Throwable.
   * 
   * @param t
   *          the Throwable to wrap
   */
  public MetaverseAnalyzerException( Throwable t ) {
    super( t );
  }

  /**
   * Instantiates a new metaverse analyzerexception with the specified message and an underlying exception
   * @param message the message
   * @param t the Throwable to wrap
   */
  public MetaverseAnalyzerException( String message, Throwable t ) {
    super( message, t );
  }

}