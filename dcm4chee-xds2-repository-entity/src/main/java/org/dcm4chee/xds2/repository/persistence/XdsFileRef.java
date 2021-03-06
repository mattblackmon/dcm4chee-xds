/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is part of dcm4che, an implementation of DICOM(TM) in
 * Java(TM), hosted at https://github.com/gunterze/dcm4che.
 *
 * The Initial Developer of the Original Code is
 * Agfa Healthcare.
 * Portions created by the Initial Developer are Copyright (C) 2011
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 * See @authors listed below
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */

package org.dcm4chee.xds2.repository.persistence;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.dcm4chee.storage.entity.BaseFileRef;

/**
 * @author Damien Evans <damien.daddy@gmail.com>
 * @author Justin Falk <jfalkmu@gmail.com>
 * @author Gunter Zeilinger <gunterze@gmail.com>
 * @author Franz Willer <franz.willer@gmail.com>
 */
@NamedQueries({
    @NamedQuery(
            name="XdsFileRef.findByDocumentUID",
            query="SELECT f FROM XdsFileRef f WHERE f.document.uid = ?1"),
    @NamedQuery(
            name="XdsFileRef.findByDocumentUIDs",
            query="SELECT f FROM XdsFileRef f WHERE f.document.uid IN (:docUIDs)"),
})

@Entity
@DiscriminatorValue("XdsFileRef")
public class XdsFileRef extends BaseFileRef {
    private static final long serialVersionUID = 6783820880409973745L;

    public static final String FIND_BY_DOCUMENT_UID ="XdsFileRef.findByDocumentUID";
    public static final String FIND_BY_DOCUMENT_UIDS ="XdsFileRef.findByDocumentUIDs";

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "object_fk")
    private XdsDocument document;

    public XdsFileRef() {};

    public XdsFileRef(String groupID, String filesystemID, String filePath, String mimetype,
            long fileSize, String digest, XdsDocument doc) {
        super(groupID, filesystemID, filePath, mimetype, fileSize, digest);
        document = doc;
    }
    public XdsDocument getDocument() {
        return document;
    }
}
