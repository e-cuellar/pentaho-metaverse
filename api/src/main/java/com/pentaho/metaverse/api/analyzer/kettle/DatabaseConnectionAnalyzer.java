/*!
 * PENTAHO CORPORATION PROPRIETARY AND CONFIDENTIAL
 *
 * Copyright 2002 - 2014 Pentaho Corporation (Pentaho). All rights reserved.
 *
 * NOTICE: All information including source code contained herein is, and
 * remains the sole property of Pentaho and its licensors. The intellectual
 * and technical concepts contained herein are proprietary and confidential
 * to, and are trade secrets of Pentaho and may be covered by U.S. and foreign
 * patents, or patents in process, and are protected by trade secret and
 * copyright laws. The receipt or possession of this source code and/or related
 * information does not convey or imply any rights to reproduce, disclose or
 * distribute its contents, or to manufacture, use, or sell anything that it
 * may describe, in whole or in part. Any reproduction, modification, distribution,
 * or public display of this information without the express written authorization
 * from Pentaho is strictly prohibited and in violation of applicable laws and
 * international treaties. Access to the source code contained herein is strictly
 * prohibited to anyone except those individuals and entities who have executed
 * confidentiality and non-disclosure agreements or other agreements with Pentaho,
 * explicitly covering such access.
 */

package com.pentaho.metaverse.api.analyzer.kettle;

import com.pentaho.dictionary.DictionaryConst;
import com.pentaho.metaverse.api.MetaverseComponentDescriptor;
import com.pentaho.metaverse.api.messages.Messages;
import org.pentaho.di.core.database.DatabaseMeta;
import com.pentaho.metaverse.api.IComponentDescriptor;
import com.pentaho.metaverse.api.ILogicalIdGenerator;
import com.pentaho.metaverse.api.IMetaverseNode;
import com.pentaho.metaverse.api.MetaverseAnalyzerException;

/**
 * DatabaseConnectionAnalyzer collects metadata about a PDI database connection
 */
public abstract class DatabaseConnectionAnalyzer<T> extends BaseKettleMetaverseComponent
    implements IDatabaseConnectionAnalyzer<T> {

  /**
   * Analyzes a database connection for metadata.
   *
   * @param dbMeta the object
   * @see com.pentaho.metaverse.api.IAnalyzer#analyze(com.pentaho.metaverse.api.IComponentDescriptor, java.lang.Object)
   */
  @Override
  public IMetaverseNode analyze( IComponentDescriptor descriptor, DatabaseMeta dbMeta )
    throws MetaverseAnalyzerException {

    if ( dbMeta == null ) {
      throw new MetaverseAnalyzerException( Messages.getString( "ERROR.DatabaseMeta.IsNull" ) );
    }

    if ( metaverseObjectFactory == null ) {
      throw new MetaverseAnalyzerException( Messages.getString( "ERROR.MetaverseObjectFactory.IsNull" ) );
    }

    if ( metaverseBuilder == null ) {
      throw new MetaverseAnalyzerException( Messages.getString( "ERROR.MetaverseBuilder.IsNull" ) );
    }

    IMetaverseNode node = createNodeFromDescriptor( descriptor );
    node.setType( DictionaryConst.NODE_TYPE_DATASOURCE );

    int accessType = dbMeta.getAccessType();
    node.setProperty( "accessType", accessType );

    String accessTypeDesc = dbMeta.getAccessTypeDesc();
    node.setProperty( "accessTypeDesc", accessTypeDesc );

    String databaseName = dbMeta.getDatabaseName();
    node.setProperty( "databaseName", databaseName );

    node.setProperty( "name", dbMeta.getName() );

    String port = dbMeta.getDatabasePortNumberString();
    node.setProperty( DictionaryConst.PROPERTY_PORT, port );

    String host = dbMeta.getHostname();
    node.setProperty( DictionaryConst.PROPERTY_HOST_NAME, host );

    String user = dbMeta.getUsername();
    node.setProperty( DictionaryConst.PROPERTY_USER_NAME, user );

    boolean shared = dbMeta.isShared();
    node.setProperty( "shared", shared );

    if ( accessTypeDesc != null && accessTypeDesc.equals( "JNDI" ) ) {
      node.setLogicalIdGenerator( DictionaryConst.LOGICAL_ID_GENERATOR_DB_JNDI );
    } else {
      node.setLogicalIdGenerator( getLogicalIdGenerator() );
    }

    metaverseBuilder.addNode( node );

    return node;

  }

  @Override
  protected ILogicalIdGenerator getLogicalIdGenerator() {
    return DictionaryConst.LOGICAL_ID_GENERATOR_DB_JDBC;
  }

  @Override
  public IComponentDescriptor buildComponentDescriptor( IComponentDescriptor parentDescriptor,
                                                        DatabaseMeta connection ) {

    IComponentDescriptor dbDescriptor =
        new MetaverseComponentDescriptor( connection.getName(), DictionaryConst.NODE_TYPE_DATASOURCE, parentDescriptor
            .getNamespace(), parentDescriptor.getContext() );

    return dbDescriptor;

  }

}
