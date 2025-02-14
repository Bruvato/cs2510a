//import xml.*;
//import tester.Tester;
//
//class ExamplesXml {
//  IXML text1 = new Text("Text 1");
//  IXML text2 = new Text("Text 2");
//  IXML text3 = new Text("Text 3");
//  Attr attr1 = new Attr("attr1", "value attr1");
//  Attr attr2 = new Attr("attr2", "value attr2");
//  Attr attr3 = new Attr("attr3", "value attr3");
//  ILoAttr emptyAttr = new MtLoAttr();
//  ILoAttr attr12 = new ConsLoAttr(attr1, new ConsLoAttr(attr2, emptyAttr));
//  ILoAttr attr21 = new ConsLoAttr(attr2, new ConsLoAttr(attr1, emptyAttr));
//  ILoAttr loattr1 = new ConsLoAttr(attr1, emptyAttr);
//  ILoAttr loattr2 = new ConsLoAttr(attr2, emptyAttr);
//
//  ILoAttr illegalAttr = new ConsLoAttr(attr2,
//      new ConsLoAttr(new Attr("attr2", "value attr1"), emptyAttr));
//  ILoAttr illegalAttrWithSameValue = new ConsLoAttr(attr2, new ConsLoAttr(attr2, emptyAttr));
//  ILoXML emptyXML = new MtLoXML();
//  ILoXML XMLText12 = new ConsLoXML(text1, new ConsLoXML(text2, emptyXML));
//  ILoXML XMLText21 = new ConsLoXML(text2, new ConsLoXML(text1, emptyXML));
//  ILoXML XMLText23 = new ConsLoXML(text2, new ConsLoXML(text3, emptyXML));
//  ILoXML XMLText123 = new ConsLoXML(text1, new ConsLoXML(text2, new ConsLoXML(text1, emptyXML)));
//  IXML tag0 = new Tag("Tag0", emptyAttr, emptyXML);
//  IXML tag1 = new Tag("Tag1", attr12, emptyXML);
//  IXML tag2 = new Tag("Tag2", attr21, emptyXML);
//  IXML tag3 = new Tag("Tag3", attr12, XMLText12);
//  IXML tag4 = new Tag("Tag4", emptyAttr, XMLText23);
//  ILoXML XMLTag13 = new ConsLoXML(tag1, new ConsLoXML(tag3, emptyXML));
//  ILoXML XMLTag43 = new ConsLoXML(tag4, new ConsLoXML(tag3, emptyXML));
//  IXML tag5 = new Tag("Tag5", emptyAttr, XMLTag13);
//  IXML tag6 = new Tag("Tag6", emptyAttr, XMLTag43);
//  IXML tag7 = new Tag("Tag7", emptyAttr, XMLText123);
//
//  boolean testConstructor(Tester t) {
//    return t.checkConstructorExceptionType(IllegalArgumentException.class, "Tag", "Illegal",
//        illegalAttr, XMLText12)
//        && t.checkConstructorExceptionType(IllegalArgumentException.class, "Tag", "Illegal",
//            illegalAttrWithSameValue, XMLText12);
//  }
//
//  boolean testSameDocument(Tester t) {
//    ILoAttr attr1 = new ConsLoAttr(new Attr("abc", "123"), emptyAttr);
//    IXML tag1Attr = new Tag("name", attr1, emptyXML);
//
//    ILoAttr attr3 = new ConsLoAttr(new Attr("a", "1"),
//        new ConsLoAttr(new Attr("b", "1"), new ConsLoAttr(new Attr("c", "1"), emptyAttr)));
//    IXML tag3Attr = new Tag("name", attr3, emptyXML);
//
//    IXML tagswap = new Tag("name", attr12,
//        new ConsLoXML(new Tag("name", attr12, emptyXML), emptyXML));
//    IXML tagswap1 = new Tag("name", loattr1,
//        new ConsLoXML(new Tag("name", loattr2, emptyXML), emptyXML));
//    IXML tagswap2 = new Tag("name", loattr1,
//        new ConsLoXML(new Tag("name", loattr2, emptyXML), emptyXML));
//
//    return t.checkExpect(text1.sameDocument(text1), true)
//        && t.checkExpect(text1.sameDocument(new Text("Text 1")), true)
//        && t.checkExpect(text1.sameDocument(text2), false)
//        && t.checkExpect(text1.sameDocument(tag0), false)
//        && t.checkExpect(tag0.sameDocument(new Tag("Tag0", emptyAttr, emptyXML)), true)
//        && t.checkExpect(tag1.sameDocument(tag2), false)
//        && t.checkExpect(tag1.sameDocument(new Tag("Tag1", attr12, emptyXML)), true)
//        // diff attr names/values
//        && t.checkExpect(tag3Attr.sameDocument(new Tag("name",
//            new ConsLoAttr(new Attr("a", "1"),
//                new ConsLoAttr(new Attr("b", "1"),
//                    new ConsLoAttr(new Attr("c", "not 1"), emptyAttr))),
//            emptyXML)), false)
//        && t.checkExpect(tag1
//            .sameDocument(new Tag("Tag1", attr12, emptyXML)), true)
//        && t.checkExpect(tag3Attr.sameDocument(new Tag("name",
//            new ConsLoAttr(new Attr("a", "1"),
//                new ConsLoAttr(new Attr("b", "1"),
//                    new ConsLoAttr(new Attr("not c", "1"), emptyAttr))),
//            emptyXML)), false)
//        && t.checkExpect(tag1Attr.sameDocument(new Tag("name", attr1, emptyXML)), true)
//        && t.checkExpect(tag1Attr.sameDocument(
//            new Tag("name", new ConsLoAttr(new Attr("abcd", "123"), emptyAttr), emptyXML)), false)
//        && t.checkExpect(
//            tag1Attr.sameDocument(new Tag("name",
//                new ConsLoAttr(new Attr("abc", "1234"), emptyAttr), emptyXML)),
//            false)
//        && t.checkExpect(tagswap.sameDocument(new Tag("name", attr12,
//            new ConsLoXML(new Tag("name", attr12, emptyXML), emptyXML))), true)
//        && t.checkExpect(
//            tagswap
//                .sameDocument(new Tag("name", attr12,
//                    new ConsLoXML(new Tag("name", attr21, emptyXML), emptyXML))),
//            false)
//        && t.checkExpect(tagswap1.sameDocument(
//            new Tag("name", loattr1, new ConsLoXML(new Tag("name", loattr2, emptyXML), emptyXML))),
//            true)
//        && t.checkExpect(tagswap1.sameDocument(
//            new Tag("name", loattr2, new ConsLoXML(new Tag("name", loattr1, emptyXML), emptyXML))),
//            false);
//
//  }
//
//  boolean testSameDocDiffTag(Tester t) {
//    return t.checkExpect(tag0.sameDocument(new Tag("not Tag0", emptyAttr, emptyXML)), false);
//  }
//
//  boolean testSameDocumentNested(Tester t) {
//    IXML tag8 = new Tag("t", emptyAttr, new ConsLoXML(new Tag("t", emptyAttr, emptyXML), emptyXML));
//    IXML tag9 = new Tag("t", emptyAttr,
//        new ConsLoXML(new Tag("t", emptyAttr, new ConsLoXML(new Text("a"), emptyXML)), emptyXML));
//    IXML tag10 = new Tag("t", emptyAttr, new ConsLoXML(new Tag("t", emptyAttr, emptyXML),
//        new ConsLoXML(new Tag("t", emptyAttr, emptyXML), emptyXML)));
//
//    return t.checkExpect(
//        tag8.sameDocument(
//            new Tag("t", emptyAttr, new ConsLoXML(new Tag("t", emptyAttr, emptyXML), emptyXML))),
//        true)
//        && t.checkExpect(tag9,
//            new Tag("t", emptyAttr,
//                new ConsLoXML(new Tag("t", emptyAttr, new ConsLoXML(new Text("a"), emptyXML)),
//                    emptyXML)))
//        && t.checkExpect(tag10,
//            new Tag("t", emptyAttr, new ConsLoXML(new Tag("t", emptyAttr, emptyXML),
//                new ConsLoXML(new Tag("t", emptyAttr, emptyXML), emptyXML))));
//  }
//
//  boolean testSameDocumentDiffOrderOfAttr(Tester t) {
//    return t.checkExpect(tag1.sameDocument(new Tag("Tag1", attr21, emptyXML)), false);
//  }
//
//  boolean testSameXML(Tester t) {
//    return t.checkExpect(text1.sameXML(new Text("Text 1")), true)
//        && t.checkExpect(text1.sameXML(text2), false) && t.checkExpect(text1.sameXML(tag0), false)
//        && t.checkExpect(tag1.sameXML(tag2), false)
//        && t.checkExpect(tag1.sameXML(new Tag("Tag1", attr21, emptyXML)), true)
//        && t.checkExpect(tag1.sameXML(new Tag("Tag1", attr12, emptyXML)), true)
//        && t.checkExpect(
//            (new Tag("a", attr12, emptyXML)).sameDocument(
//                new Tag("a", loattr1, new ConsLoXML(new Tag("a", loattr2, emptyXML), emptyXML))),
//            false)
//        && t.checkExpect(
//            (new Tag("a", loattr1, new ConsLoXML(new Tag("a", loattr2, emptyXML), emptyXML)))
//                .sameDocument(new Tag("a", loattr2,
//                    new ConsLoXML(new Tag("a", loattr1, emptyXML), emptyXML))),
//            false);
//  }
//
//  boolean testSameXMLDiffOrderText(Tester t) {
//    return t.checkExpect(tag3.sameXML(new Tag("Tag3", attr21, XMLText12)), true)
//        && t.checkExpect(tag3.sameXML(new Tag("Tag3", attr12, XMLText21)), false);
//  }
//
//  boolean testSameXMLNested(Tester t) {
//    IXML nested = new Tag("t", emptyAttr, new ConsLoXML(new Tag("t", attr21, emptyXML), emptyXML));
//    return t.checkExpect(nested,
//        new Tag("t", emptyAttr, new ConsLoXML(new Tag("t", attr12, emptyXML), emptyXML)));
//  }
//
//  boolean testSameText(Tester t) {
//    return t.checkExpect(tag6.sameText(tag7), false);
//  }
//
//  boolean testSameTextMt(Tester t) {
//    return t.checkExpect(tag0.sameText(new Tag("Tag3", attr21, emptyXML)), true);
//  }
//
////  boolean testSameText(Tester t) {
////    return t.checkExpect(
////        new Tag("1(23)", emptyAttr, new ConsLoXML(text1, new ConsLoXML(tag4, emptyXML))).sameText(
////            new Tag("1(23)", emptyAttr, new ConsLoXML(text1, new ConsLoXML(tag4, emptyXML)))),
////        true);
////  }
//
//}
