import tester.Tester;

// TODO
// NOTE: sameness in all purpose statements refer
// to all fields of a object being equal to all fields of another object in the same order
// unless otherwise specified

// represents a XML document
interface IXML {

  // determines if this XML document and the given document have exactly the same
  // shape: the same tags, the same tree-structure, the same attributes in the
  // same order with the same values, and the same content.
  boolean sameDocument(IXML doc);

  // determines if this XML doc and the given doc have equivalent shape, when
  // attribute-ordering is irrelevant
  boolean sameXML(IXML doc);

  // determines if this document and the given document contain the same plain
  // text content
  boolean sameText(IXML doc);

  // ----------------------------------

  // determines if this XML doc is the same as a given text element
  boolean sameTextElem(Text text);

  // determines if this XML doc is the same shape as a given text element
  // (ignoring attribute order)
  boolean sameXMLTextElem(Text text);

  // determines if this XML doc is the same as a given tag element
  boolean sameTagElem(Tag tag);

  // determines if this XML doc is the same shape as a given tag element (ignoring
  // attribute order)
  boolean sameXMLTagElem(Tag tag);

  // appends all the text in this XML doc
  String appendAllText();

}

// represents a text element
class Text implements IXML {
  String content;

  public Text(String content) {
    this.content = content;
  }

  // determines if this text element is the same as a given XML doc
  public boolean sameDocument(IXML doc) {
    return doc.sameTextElem(this);
  }

  // determines if this text element has the same shape as a given XML doc
  // (ignoring attribute order)
  public boolean sameXML(IXML doc) {
    return doc.sameXMLTextElem(this);
  }

  // determines if this text element's plain text is the same as a given XML doc's
  // plain text
  public boolean sameText(IXML doc) {
    return this.appendAllText().equals(doc.appendAllText());
  }

  // -----------------------------------

  // determines if this text element is the same as a given text
  public boolean sameTextElem(Text text) {
    return this.content.equals(text.content);
  }

  // determines if this text element is the same shape as a given text (ignoring
  // attribute order)
  public boolean sameXMLTextElem(Text text) {
    return this.content.equals(text.content);
  }

  // determines if this text is the same as a given tag
  public boolean sameTagElem(Tag tag) {
    return false;
  }

  // determines if this text element is the same shape as a given tag element
  // (ignoring attribute order)
  public boolean sameXMLTagElem(Tag tag) {
    return false;
  }

  // appends all the text in this text element
  public String appendAllText() {
    return this.content;
  }

}

// represents a tag element
class Tag implements IXML {
  String name;
  ILoAttr attrs;
  ILoXML content;

  public Tag(String name, ILoAttr attrs, ILoXML content) {

    if (!attrs.sameLoAttr(attrs.removeDuplicates())) {
      throw new IllegalArgumentException(
          "attribute names within an individual tag must be unique: " + attrs);
    }
    this.name = name;
    this.attrs = attrs;
    this.content = content;
  }

  // determines if this tag is the same as a given XML doc
  public boolean sameDocument(IXML doc) {
    return doc.sameTagElem(this);
  }

  // determines if this tag element has the same shape as a given XML doc
  // (ignoring attribute order)
  public boolean sameXML(IXML doc) {
    return doc.sameXMLTagElem(this);
  }

  // determines if this tag element's plain text is the same as a given XML doc's
  // text
  public boolean sameText(IXML doc) {
    return this.content.appendAllText().equals(doc.appendAllText());
  }

  // -------------------------------------

  // determines if this tag element is the same as a given text element
  public boolean sameTextElem(Text text) {
    return false;
  }

  // determines if this tag element is the same shape as a given text element
  // (ignoring attribute order)
  public boolean sameXMLTextElem(Text text) {
    return false;
  }

  // determines if this tag is the same as a given tag
  public boolean sameTagElem(Tag tag) {
    return this.name.equals(tag.name) && this.attrs.sameLoAttr(tag.attrs)
        && this.content.sameLoXML(tag.content);
  }

  // determines if this tag element is the same shape as a given tag element
  // (ignoring attribute order)
  public boolean sameXMLTagElem(Tag tag) {
    // ignore name
    return this.attrs.sameSetAttr(tag.attrs) && this.content.sameXMLLoXML(tag.content);
  }

  // appends all the text in this tag element
  public String appendAllText() {
    return content.appendAllText();
  }

}

// represents a attribute of a tag
class Attr {
  String name;
  String value;

  public Attr(String name, String value) {
    this.name = name;
    this.value = value;
  }

  // determines if this attribute is the same as a given attribute
  boolean sameAttr(Attr attr) {
    return this.name.equals(attr.name) && this.value.equals(attr.value);
  }

  // determines if this attribute's name is the same as a given attribute's name
  boolean sameAttrName(Attr attr) {
    return this.name.equals(attr.name);
  }
}

// represents a list of XML content
interface ILoXML {

  // determines if this list of XML is the same as a given list of XML
  boolean sameLoXML(ILoXML loXML);

  // determines if this list of XML is the same shape as a given list of XML
  // (ignoring attribute order)
  boolean sameXMLLoXML(ILoXML loXML);

  // determines if this list of XML is the same as a given empty list of XML
  boolean sameMtLoXML(MtLoXML mtLoXML);

  // determines if this list of XML is the same as a given non empty list of XML
  boolean sameConsLoXML(ConsLoXML consLoXML);

  // determines if this list of XML is the same shape as a given non empty list of
  // XML (ignoring attribute order)
  boolean sameXMLConsLoXML(ConsLoXML consLoXML);

  // appends all the text in this list of XML
  String appendAllText();

}

// represents an empty list of XML content
class MtLoXML implements ILoXML {

  // determines if this empty list of XML is the same as a given list of XML
  public boolean sameLoXML(ILoXML loXML) {
    return loXML.sameMtLoXML(this);
  }

  // determines if this empty list of XML is the same shape as a given list of XML
  // (ignoring attribute order)
  public boolean sameXMLLoXML(ILoXML loXML) {
    return loXML.sameMtLoXML(this);
  }

  // determines if this empty list of XML is the same as a given empty list of XML
  public boolean sameMtLoXML(MtLoXML mtLoXML) {
    return true;
  }

  // determines if this empty list of XML is the same as a given non empty list of
  // XML
  public boolean sameConsLoXML(ConsLoXML consLoXML) {
    return false;
  }

  // determines if this empty list of XML is the same shape as a given non empty
  // list of XML (ignoring attribute order)
  public boolean sameXMLConsLoXML(ConsLoXML consLoXML) {
    return false;
  }

  // appends all the text in this empty list of XML
  public String appendAllText() {
    return "";
  }

}

// represents a non empty list of XML content
class ConsLoXML implements ILoXML {
  IXML first;
  ILoXML rest;

  public ConsLoXML(IXML first, ILoXML rest) {
    this.first = first;
    this.rest = rest;
  }

  // determines if this non empty list of XML is the same as a given list of XML
  public boolean sameLoXML(ILoXML loXML) {
    return loXML.sameConsLoXML(this);
  }

  // determines if this non empty list of XML is the same shape as a given list of
  // XML (ignoring attribute order)
  public boolean sameXMLLoXML(ILoXML loXML) {
    return loXML.sameXMLConsLoXML(this);
  }

  // determines if this non empty list of XML is the same as a given empty list of
  // XML
  public boolean sameMtLoXML(MtLoXML mtLoXML) {
    return false;
  }

  // determines if this non empty list of XML is the same as a given non empty
  // list of XML
  public boolean sameConsLoXML(ConsLoXML consLoXML) {
    return this.first.sameDocument(consLoXML.first) && this.rest.sameLoXML(consLoXML.rest);
  }

  // determines if this non empty list of XML is the same shape as a given non
  // empty list of XML (ignoring attribute order)
  public boolean sameXMLConsLoXML(ConsLoXML consLoXML) {
    return this.first.sameXML(consLoXML.first) && this.rest.sameXMLLoXML(consLoXML.rest);
  }

  // appends all the text in this non empty list of XML
  public String appendAllText() {
    return this.first.appendAllText() + this.rest.appendAllText();
  }

}

// represents a list of attributes
interface ILoAttr {

  // determines if this list of attributes is the same as a given list of
  // attributes
  boolean sameLoAttr(ILoAttr loAttr);

  // determines if this list of attributes is the same as a given list of
  // attributes (ignoring attribute order)
  boolean sameSetAttr(ILoAttr loAttr);

  // determines if this list of attributes is the same as a given non empty list
  // of attributes
  boolean sameConsLoAttr(ConsLoAttr consLoAttr);

  // determines if this list of attributes is the same as a given non empty list
  // of attributes (ignoring attribute order)
  boolean sameConsSetAttr(ConsLoAttr consLoAttr);

  // determines if this list of attributes is the same as a given empty list of
  // attributes
  boolean sameMtLoAttr(MtLoAttr mtLoAttr);

  // removes all duplicates in this list of attributes
  ILoAttr removeDuplicates();

  // determines if this list of attributes contains a attribute with the same name
  // as a given attribute
  boolean containsAttrName(Attr attr);

  // determines if this list of attributes contains a given attribute
  boolean containsAttr(Attr attr);

  // -----

  // determines if this list of attributes is a subset of a given list of
  // attributes
  boolean subset(ILoAttr loAttr);

  // determines if this list of attributes is a subset of a given empty list of
  // attributes
  boolean superSetMt(MtLoAttr mtLoAttr);

  // determines if this list of attributes is a subset of given non empty list of
  // attributes
  boolean superSetCons(ConsLoAttr consLoAttr);

}

// represents an empty list of attributes
class MtLoAttr implements ILoAttr {

  // determines if this empty list of attributes is the same as a given list of
  // attributes
  public boolean sameLoAttr(ILoAttr loAttr) {
    return loAttr.sameMtLoAttr(this);
  }

  // determines if this empty list of attributes is the same as a given list of
  // attributes (ignoring attribute order)
  public boolean sameSetAttr(ILoAttr loAttr) {
    return loAttr.sameMtLoAttr(this);
  }

  // determines if this empty list of attributes is the same as a given non empty
  // list of attributes
  public boolean sameConsLoAttr(ConsLoAttr consLoAttr) {
    return false;
  }

  // determines if this empty list of attributes is the same as a given non empty
  // list of attributes (ignoring attribute order)
  public boolean sameConsSetAttr(ConsLoAttr consLoAttr) {
    return false;
  }

  // determines if this empty list of attributes is the same as a given empty list
  // of attributes
  public boolean sameMtLoAttr(MtLoAttr mtLoAttr) {
    return true;
  }

  // removes all duplicates in this empty list of attributes
  public ILoAttr removeDuplicates() {
    return this;
  }

  // determines if this empty list of attributes contains a attribute with the
  // same name as a given attribute
  public boolean containsAttrName(Attr attr) {
    return false;
  }

  // determines if this empty list of attributes contains a given attribute
  public boolean containsAttr(Attr attr) {
    return false;
  }

  // ---

  // determines if this empty list of attributes is a subset of a given list of
  // attributes
  public boolean subset(ILoAttr loAttr) {
    return loAttr.superSetMt(this);
  }

  // determines if this empty list of attributes is a super set of a given empty
  // list of attributes
  public boolean superSetMt(MtLoAttr mtLoAttr) {
    // empty is subset of empty
    return true;
  }

  // determines if this empty list of attributes is a superset of a given non
  // empty list of attributes
  public boolean superSetCons(ConsLoAttr consLoAttr) {
    // empty is not superset of non empty
    return false;
  }

}

// represents a non empty list of attributes
class ConsLoAttr implements ILoAttr {
  Attr first;
  ILoAttr rest;

  public ConsLoAttr(Attr first, ILoAttr rest) {
    this.first = first;
    this.rest = rest;
  }

  // determines if this non empty list of attributes is the same as a given list
  // off attributes
  public boolean sameLoAttr(ILoAttr loAttr) {
    return loAttr.sameConsLoAttr(this);
  }

  // determines if this non empty list of attributes is the same as a given list
  // of attributes (ignoring order)
  public boolean sameSetAttr(ILoAttr loAttr) {
    return loAttr.sameConsSetAttr(this);
  }

  // determines if this non empty list of attributes is the same as a given non
  // empty list of attributes
  public boolean sameConsLoAttr(ConsLoAttr consLoAttr) {
    return this.first.sameAttr(consLoAttr.first) && this.rest.sameLoAttr(consLoAttr.rest);
  }

  // determines if this non empty list of attributes is the same as a given non
  // empty list of attributes (ignoring order)
  public boolean sameConsSetAttr(ConsLoAttr consLoAttr) {

    return this.subset(consLoAttr) && consLoAttr.subset(this);
  }

  // determines if this non empty list of attributes is the same as a given empty
  // list of attributes
  public boolean sameMtLoAttr(MtLoAttr mtLoAttr) {
    return false;
  }

  // removes all duplicates in this non empty list of attributes
  public ILoAttr removeDuplicates() {
    if (this.rest.containsAttrName(this.first)) {
      return this.rest;
    }
    return new ConsLoAttr(this.first, this.rest.removeDuplicates());
  }

  // determines if this non empty list of attributes contains a attribute with the
  // same name as a given attribute
  public boolean containsAttrName(Attr attr) {
    return this.first.sameAttrName(attr) || this.rest.containsAttrName(attr);
  }

  // determines if this non empty list of attributes contains a given attribute
  public boolean containsAttr(Attr attr) {
    return this.first.sameAttr(attr) || this.rest.containsAttrName(attr);
  }

  // ---

  // determines if this non empty list of attributes is a subset of a given list
  // of attributes
  public boolean subset(ILoAttr loAttr) {
    return loAttr.superSetCons(this);
  }

  // determines if this non empty list of attributes is a superset of a given
  // empty list of attributes
  public boolean superSetMt(MtLoAttr mtLoAttr) {
    // non empty is superset of empty
    return true;
  }

  // determines if this non empty list of attributes is a superset of a given non
  // empty list of attributes
  public boolean superSetCons(ConsLoAttr consLoAttr) {
    return this.containsAttr(consLoAttr.first) && consLoAttr.rest.subset(this);
  }

}

// TODO
//  Note: Next to each test case 
// (whether that’s a particular data example or a particular check-expect),
// leave a comment explaining why that example is interesting, distinctive, or necessary:
// what scenario is it trying to test for?

class ExamplesXml {

  // Empty -------------------------
  ILoXML mtLoXML = new MtLoXML();
  ILoAttr mtLoAttr = new MtLoAttr();

  // Text --------------------------
  Text emptyStr = new Text("");
  Text hello = new Text("hello");
  Text cool = new Text("cool");

  // XML text ------------------
  IXML txt0 = emptyStr;
  IXML txt1 = hello;
  IXML txt2 = cool;

  // Attributes ---------------------------------
  Attr attrId = new Attr("id", "123");
  Attr attrSrc = new Attr("src", "donut.gif");
  Attr attrType = new Attr("type", "gif");

  // List of Attributes -----------------------------
  // -- size 1
  ILoAttr loAttrId = new ConsLoAttr(attrId, mtLoAttr);
  // -- size 2
  ILoAttr loAttrIdSrc = new ConsLoAttr(attrId, new ConsLoAttr(attrSrc, mtLoAttr));
  ILoAttr loAttrSrcId = new ConsLoAttr(attrSrc, new ConsLoAttr(attrId, mtLoAttr));
  // -- size 3
  ILoAttr loAttrIdSrcType = new ConsLoAttr(attrId,
      new ConsLoAttr(attrSrc, new ConsLoAttr(attrType, mtLoAttr)));
  ILoAttr loAttrTypeIdSrc = new ConsLoAttr(attrType,
      new ConsLoAttr(attrId, new ConsLoAttr(attrSrc, mtLoAttr)));
  ILoAttr loAttrIdId = new ConsLoAttr(attrId, new ConsLoAttr(attrId, mtLoAttr));

  // List of XML text -------------------------
  // -- size 1
  ILoXML loXml0 = new ConsLoXML(txt0, mtLoXML);
  // -- size 2
  ILoXML loXml1 = new ConsLoXML(txt0, new ConsLoXML(txt1, mtLoXML));
  ILoXML loXml2 = new ConsLoXML(txt1, new ConsLoXML(txt0, mtLoXML));
  // -- size 3
  ILoXML loXml3 = new ConsLoXML(txt0, new ConsLoXML(txt1, new ConsLoXML(txt2, mtLoXML)));
  ILoXML loXml4 = new ConsLoXML(txt2, new ConsLoXML(txt1, new ConsLoXML(txt0, mtLoXML)));

  // Tags ------------------------------------
  // -- no attributes, no content
  Tag tag0 = new Tag("p", mtLoAttr, mtLoXML);

  // -- has attributes no content
  Tag tag1 = new Tag("p", loAttrId, mtLoXML);
  Tag tag2 = new Tag("p", loAttrIdSrc, mtLoXML);
  Tag tag3 = new Tag("p", loAttrIdSrcType, mtLoXML);
  Tag tag4 = new Tag("p", loAttrTypeIdSrc, mtLoXML);
  // IXML xmlIllegal = new Tag("p", loAttrIdId, mtLoXML);

  // -- no attributes, has content
  Tag tag5 = new Tag("p", mtLoAttr, loXml0);
  Tag tag6 = new Tag("p", mtLoAttr, loXml3);

  // -- has attributes, has content
  Tag tag7 = new Tag("p", loAttrId, loXml0);
  Tag tag8 = new Tag("p", loAttrIdSrcType, loXml3);

  // XML -------------------------------------------
  IXML xml1 = new Tag("name", new ConsLoAttr(attrId, mtLoAttr), new ConsLoXML(txt2, mtLoXML));
  IXML xml2 = new Tag("p", new ConsLoAttr(attrId, mtLoAttr), new ConsLoXML(xml1, mtLoXML));

  IXML xml3 = new Tag("name", new ConsLoAttr(attrId, new ConsLoAttr(attrSrc, mtLoAttr)), mtLoXML);
  IXML xml4 = new Tag("not name", new ConsLoAttr(attrSrc, new ConsLoAttr(attrId, mtLoAttr)),
      mtLoXML);
  //
  IXML xml5 = new Tag("name", loAttrId, loXml1);
  IXML xml6 = new Tag("name", loAttrIdSrc, loXml1);
  IXML xml7 = new Tag("name", loAttrSrcId, loXml1);
  //
  IXML xml8 = new Tag("name", mtLoAttr, loXml1);
  IXML xml9 = new Tag("name", mtLoAttr, loXml2);
  IXML xml10 = new Tag("name", mtLoAttr, loXml3);
  IXML xml11 = new Tag("name", mtLoAttr, loXml4);

  // XML tests ---------------------------

  boolean testConstructor(Tester t) {
    return t.checkConstructorExceptionType(IllegalArgumentException.class, "Tag", "Illegal",
        loAttrIdId, mtLoXML);
  }

  boolean testSameDocument(Tester t) {
    return
    // XML text
    t.checkExpect(txt0.sameDocument(txt0), true) && t.checkExpect(txt0.sameDocument(txt1), false)
        && t.checkExpect(txt0.sameDocument(tag0), false)
        // XML tag
        && t.checkExpect(tag0.sameDocument(tag0), true)
        && t.checkExpect(tag1.sameDocument(tag1), true)
        && t.checkExpect(tag1.sameDocument(tag2), false);

  }

  boolean testSameXML(Tester t) {
    return
    // test if order matters - attribute ordering is ignored
    t.checkExpect(tag3.sameXML(tag4), true)
        // test if order matters - attribute ordering is ignored
        && t.checkExpect(xml6.sameXML(xml7), true)
        // test different texts
        && t.checkExpect((new Text("hello world!")).sameXML(new Text("hello")), false);
  }

  boolean testSameText(Tester t) {
    return
    // test itself
    t.checkExpect(xml5.sameText(xml5), true)
        // test if attributes matter - no effect
        && t.checkExpect(xml5.sameText(xml6), true)
        // test if order of text matters with empty string - empty string no effect
        && t.checkExpect(xml8.sameText(xml9), true)
        // test if order of nonempty strings matters - order matters
        && t.checkExpect(xml10.sameText(xml11), false);

  }

  boolean testSameTextElem(Tester t) {
    return
    // check if two empty strings are the same
    t.checkExpect(emptyStr.sameTextElem(emptyStr), true)
        // check if two strings are the same
        && t.checkExpect(hello.sameTextElem(hello), true)
        && t.checkExpect(hello.sameTextElem(cool), false);
  }

  boolean testSameXMLTextElem(Tester t) {
    return
    // test different text - text value matters
    t.checkExpect((new Text("hello world!")).sameXMLTextElem(new Text("hello")), false);
  }

  boolean testSameTagElem(Tester t) {
    return
    // test if two tags are the same
    t.checkExpect(tag0.sameTagElem(tag0), true)
        // test different name - names matter
        && t.checkExpect(tag0.sameTagElem(new Tag("pa", mtLoAttr, mtLoXML)), false);
  }

  boolean testSameXmlTagElem(Tester t) {
    return
    // test itself
    t.checkExpect(tag0.sameXMLTagElem(tag0), true)
        // test if name matters - name is ignored
        && t.checkExpect(tag0.sameXMLTagElem(new Tag("pa", mtLoAttr, mtLoXML)), true)
        // test if attribute ordering matters - attribute ordering is ignored
        && t.checkExpect(tag3.sameXMLTagElem(tag4), true);
  }

  boolean testAppendAllText(Tester t) {
    return t.checkExpect(xml11.appendAllText(), "coolhello");
  }

  // Attributes tests -----------------------

  boolean testSameAttr(Tester t) {
    return
    // test itself
    t.checkExpect(attrId.sameAttr(attrId), true)
        // test different name/value
        && t.checkExpect(attrId.sameAttr(attrSrc), false)
        // test different name - must have same name
        && t.checkExpect(attrId.sameAttr(new Attr("id a", "123")), false)
        // test different value - must have same value
        && t.checkExpect(attrId.sameAttr(new Attr("id", "123a")), false);
  }

  boolean testSameAttrName(Tester t) {
    return
    // test itself
    t.checkExpect(attrId.sameAttrName(attrId), true)
        // test different name/value
        && t.checkExpect(attrId.sameAttrName(attrSrc), false)
        // test different name - must have same name
        && t.checkExpect(attrId.sameAttrName(new Attr("id a", "123")), false)
        // test different value - value is ignored
        && t.checkExpect(attrId.sameAttrName(new Attr("id", "123a")), true);
  }

  // Attribute Lists tests -----------------------------------------

  boolean testSameLoAttr(Tester t) {
    return t.checkExpect(mtLoAttr.sameLoAttr(mtLoAttr), true)
        && t.checkExpect(mtLoAttr.sameLoAttr(loAttrId), false)
        && t.checkExpect(loAttrIdSrc.sameLoAttr(loAttrIdSrc), true)
        // test order - order matters
        && t.checkExpect(loAttrIdSrc.sameLoAttr(loAttrSrcId), false);
  }

  boolean testSameSetAttr(Tester t) {
    return t.checkExpect(mtLoAttr.sameSetAttr(mtLoAttr), true)
        && t.checkExpect(mtLoAttr.sameSetAttr(loAttrId), false)
        && t.checkExpect(loAttrIdSrc.sameSetAttr(loAttrIdSrc), true)
        // test order - order ignored
        && t.checkExpect(loAttrIdSrc.sameSetAttr(loAttrSrcId), true);
  }

  boolean testSameConsLoAttr(Tester t) {
    return t.checkExpect(loAttrId.sameConsLoAttr(new ConsLoAttr(attrId, mtLoAttr)), true);
  }

  boolean testSameConsSetAttr(Tester t) {
    return
    // order ignored
    t.checkExpect(
        loAttrIdSrc.sameConsSetAttr(new ConsLoAttr(attrSrc, new ConsLoAttr(attrId, mtLoAttr))),
        true);
  }
  
  boolean testSameMtLoAttr(Tester t) {
    return t.checkExpect(mtLoAttr.sameMtLoAttr(new MtLoAttr()), true);
  }

  boolean testRemoveDup(Tester t) {
    return t.checkExpect(mtLoAttr.removeDuplicates(), mtLoAttr)
        && t.checkExpect(loAttrId.removeDuplicates(), loAttrId)
        && t.checkExpect(loAttrIdId.removeDuplicates(), loAttrId);
  }

  boolean testContainsAttr(Tester t) {
    return
    // empty contains nothing
    t.checkExpect(mtLoAttr.containsAttr(attrId), false)
        && t.checkExpect(loAttrId.containsAttr(attrId), true)
        && t.checkExpect(loAttrIdSrc.containsAttr(attrId), true);
  }

  boolean testContainsAttrName(Tester t) {
    return
    // empty contains nothing
    t.checkExpect(mtLoAttr.containsAttrName(attrId), false)
        && t.checkExpect(loAttrId.containsAttrName(attrId), true)
        && t.checkExpect(loAttrIdSrc.containsAttrName(attrId), true)
        // test different value - ignore value
        && t.checkExpect(loAttrIdSrc.containsAttrName(new Attr("id", "123a")), true);
  }

  boolean testSubset(Tester t) {
    return
    // empty subset of empty
    t.checkExpect(mtLoAttr.subset(mtLoAttr), true)
        // empty subset of nonempty
        && t.checkExpect(mtLoAttr.subset(loAttrId), true)
        && t.checkExpect(loAttrId.subset(loAttrIdSrc), true);
  }
  
  boolean testSuperSetMt(Tester t) {
    return
        // empty superset of empty
        t.checkExpect(mtLoAttr.superSetMt(new MtLoAttr()), true);
  }
  
  boolean testSuperSetCons(Tester t) {
    return t.checkExpect(loAttrIdSrc.superSetCons(new ConsLoAttr(attrId, mtLoAttr)), true);
  }

  // XML lists tests -------------------

  boolean testSameLoXML(Tester t) {
    return t.checkExpect(mtLoXML.sameLoXML(mtLoXML), true)
        && t.checkExpect(mtLoXML.sameLoXML(loXml0), false)
        && t.checkExpect(loXml0.sameLoXML(loXml0), true)
        // test order - order matters
        && t.checkExpect(loXml1.sameLoXML(loXml2), false);
  }

  boolean testSameXMLLoXML(Tester t) {
    return t.checkExpect(mtLoXML.sameXMLLoXML(mtLoXML), true)
        && t.checkExpect(mtLoXML.sameXMLLoXML(loXml0), false)
        && t.checkExpect(loXml0.sameXMLLoXML(loXml0), true)
        // test order - order matters
        && t.checkExpect(loXml1.sameXMLLoXML(loXml2), false);
  }

  boolean testMtLoXML(Tester t) {
    return t.checkExpect(mtLoXML.sameMtLoXML(new MtLoXML()), true);
  }

  boolean testSameConsXML(Tester t) {
    return t.checkExpect(loXml0.sameConsLoXML(new ConsLoXML(txt0, mtLoXML)), true);
  }

  boolean testSameXMLConsLoXML(Tester t) {
    return t.checkExpect(loXml0.sameConsLoXML(new ConsLoXML(txt0, mtLoXML)), true);
  }

  boolean testAppendAllTextXML(Tester t) {
    return t.checkExpect(loXml2.appendAllText(), "hello");
  }
}
