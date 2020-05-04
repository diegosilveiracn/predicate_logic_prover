/* Generated By:JJTree&JavaCC: Do not edit this line. Parser.java */
        public class Parser/*@bgen(jjtree)*/implements ParserTreeConstants, ParserConstants {/*@bgen(jjtree)*/
  protected JJTParserState jjtree = new JJTParserState();

  final private void negation() throws ParseException {
 /*@bgen(jjtree) negation */
  SimpleNode jjtn000 = new SimpleNode(JJTNEGATION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(NEGATION);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          jjtn000.setToken(t);
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final private void var() throws ParseException {
 /*@bgen(jjtree) var */
  SimpleNode jjtn000 = new SimpleNode(JJTVAR);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(SMALL_ID);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          jjtn000.setToken(t);
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final private void _const() throws ParseException {
 /*@bgen(jjtree) _const */
  SimpleNode jjtn000 = new SimpleNode(JJT_CONST);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(CAPITAL_ID);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          jjtn000.setToken(t);
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final private void param() throws ParseException {
 /*@bgen(jjtree) param */
  SimpleNode jjtn000 = new SimpleNode(JJTPARAM);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      if (jj_2_1(2)) {
        func();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case CAPITAL_ID:
          _const();
          break;
        case SMALL_ID:
          var();
          break;
        default:
          jj_la1[0] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final private void paramList() throws ParseException {
 /*@bgen(jjtree) paramList */
  SimpleNode jjtn000 = new SimpleNode(JJTPARAMLIST);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      param();
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[1] = jj_gen;
          break label_1;
        }
        jj_consume_token(COMMA);
        param();
      }
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final private void func() throws ParseException {
 /*@bgen(jjtree) func */
  SimpleNode jjtn000 = new SimpleNode(JJTFUNC);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(SMALL_ID);
      jj_consume_token(LEFT_PAREN);
      paramList();
      jj_consume_token(RIGHT_PAREN);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          jjtn000.setToken(t);
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final private void pred() throws ParseException {
 /*@bgen(jjtree) pred */
  SimpleNode jjtn000 = new SimpleNode(JJTPRED);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      if (jj_2_2(2)) {
        t = jj_consume_token(CAPITAL_ID);
        jj_consume_token(LEFT_PAREN);
        paramList();
        jj_consume_token(RIGHT_PAREN);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          jjtn000.setToken(t);
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SMALL_ID:
        case CAPITAL_ID:
          param();
          t = jj_consume_token(EQUAL);
          param();
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          jjtn000.setToken(t);
          break;
        default:
          jj_la1[2] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final private void connective() throws ParseException {
 /*@bgen(jjtree) connective */
  SimpleNode jjtn000 = new SimpleNode(JJTCONNECTIVE);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        t = jj_consume_token(AND);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtn000.setToken(t);
        break;
      case OR:
        t = jj_consume_token(OR);
                   jjtree.closeNodeScope(jjtn000, true);
                   jjtc000 = false;
                   jjtn000.setToken(t);
        break;
      case IMPLICATION:
        t = jj_consume_token(IMPLICATION);
                            jjtree.closeNodeScope(jjtn000, true);
                            jjtc000 = false;
                            jjtn000.setToken(t);
        break;
      case BICONDITIONAL:
        t = jj_consume_token(BICONDITIONAL);
                              jjtree.closeNodeScope(jjtn000, true);
                              jjtc000 = false;
                              jjtn000.setToken(t);
        break;
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final private void quantifier() throws ParseException {
 /*@bgen(jjtree) quantifier */
  SimpleNode jjtn000 = new SimpleNode(JJTQUANTIFIER);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case FORALL:
        t = jj_consume_token(FORALL);
                       jjtree.closeNodeScope(jjtn000, true);
                       jjtc000 = false;
                       jjtn000.setToken(t);
        break;
      case EXISTS:
        t = jj_consume_token(EXISTS);
                       jjtree.closeNodeScope(jjtn000, true);
                       jjtc000 = false;
                       jjtn000.setToken(t);
        break;
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final private void wff() throws ParseException {
 /*@bgen(jjtree) wff */
  SimpleNode jjtn000 = new SimpleNode(JJTWFF);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      if (jj_2_3(2)) {
        jj_consume_token(LEFT_PAREN);
        pred();
        jj_consume_token(RIGHT_PAREN);
      } else if (jj_2_4(2)) {
        jj_consume_token(LEFT_PAREN);
        negation();
        wff();
        jj_consume_token(RIGHT_PAREN);
      } else if (jj_2_5(2)) {
        jj_consume_token(LEFT_PAREN);
        wff();
        connective();
        wff();
        jj_consume_token(RIGHT_PAREN);
      } else if (jj_2_6(2)) {
        jj_consume_token(LEFT_PAREN);
        quantifier();
        var();
        wff();
        jj_consume_token(RIGHT_PAREN);
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final private void wffList() throws ParseException {
 /*@bgen(jjtree) wffList */
  SimpleNode jjtn000 = new SimpleNode(JJTWFFLIST);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      wff();
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[5] = jj_gen;
          break label_2;
        }
        jj_consume_token(COMMA);
        wff();
      }
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public SimpleNode start() throws ParseException {
 /*@bgen(jjtree) start */
  SimpleNode jjtn000 = new SimpleNode(JJTSTART);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      wffList();
      jj_consume_token(0);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  final private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  final private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  final private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  final private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  final private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  final private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  final private boolean jj_3_1() {
    if (jj_3R_3()) return true;
    return false;
  }

  final private boolean jj_3R_11() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_1()) {
    jj_scanpos = xsp;
    if (jj_3R_12()) {
    jj_scanpos = xsp;
    if (jj_3R_13()) return true;
    }
    }
    return false;
  }

  final private boolean jj_3R_10() {
    if (jj_scan_token(EXISTS)) return true;
    return false;
  }

  final private boolean jj_3R_7() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_9()) {
    jj_scanpos = xsp;
    if (jj_3R_10()) return true;
    }
    return false;
  }

  final private boolean jj_3R_9() {
    if (jj_scan_token(FORALL)) return true;
    return false;
  }

  final private boolean jj_3R_14() {
    if (jj_scan_token(CAPITAL_ID)) return true;
    return false;
  }

  final private boolean jj_3R_15() {
    if (jj_scan_token(SMALL_ID)) return true;
    return false;
  }

  final private boolean jj_3R_5() {
    if (jj_scan_token(NEGATION)) return true;
    return false;
  }

  final private boolean jj_3R_8() {
    if (jj_3R_11()) return true;
    return false;
  }

  final private boolean jj_3R_4() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_2()) {
    jj_scanpos = xsp;
    if (jj_3R_8()) return true;
    }
    return false;
  }

  final private boolean jj_3_2() {
    if (jj_scan_token(CAPITAL_ID)) return true;
    if (jj_scan_token(LEFT_PAREN)) return true;
    return false;
  }

  final private boolean jj_3R_13() {
    if (jj_3R_15()) return true;
    return false;
  }

  final private boolean jj_3_6() {
    if (jj_scan_token(LEFT_PAREN)) return true;
    if (jj_3R_7()) return true;
    return false;
  }

  final private boolean jj_3_5() {
    if (jj_scan_token(LEFT_PAREN)) return true;
    if (jj_3R_6()) return true;
    return false;
  }

  final private boolean jj_3_4() {
    if (jj_scan_token(LEFT_PAREN)) return true;
    if (jj_3R_5()) return true;
    return false;
  }

  final private boolean jj_3_3() {
    if (jj_scan_token(LEFT_PAREN)) return true;
    if (jj_3R_4()) return true;
    return false;
  }

  final private boolean jj_3R_6() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_3()) {
    jj_scanpos = xsp;
    if (jj_3_4()) {
    jj_scanpos = xsp;
    if (jj_3_5()) {
    jj_scanpos = xsp;
    if (jj_3_6()) return true;
    }
    }
    }
    return false;
  }

  final private boolean jj_3R_3() {
    if (jj_scan_token(SMALL_ID)) return true;
    if (jj_scan_token(LEFT_PAREN)) return true;
    return false;
  }

  final private boolean jj_3R_12() {
    if (jj_3R_14()) return true;
    return false;
  }

  public ParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  public Token token, jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  public boolean lookingAhead = false;
  private boolean jj_semLA;
  private int jj_gen;
  final private int[] jj_la1 = new int[6];
  static private int[] jj_la1_0;
  static {
      jj_la1_0();
   }
   private static void jj_la1_0() {
      jj_la1_0 = new int[] {0x300000,0x8000,0x300000,0x3c0,0x1800,0x8000,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[6];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  public Parser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public Parser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public Parser(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  final private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  final private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }

  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  final public Token getToken(int index) {
    Token t = lookingAhead ? jj_scanpos : token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  final private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.Vector jj_expentries = new java.util.Vector();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      boolean exists = false;
      for (java.util.Enumeration e = jj_expentries.elements(); e.hasMoreElements();) {
        int[] oldentry = (int[])(e.nextElement());
        if (oldentry.length == jj_expentry.length) {
          exists = true;
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              exists = false;
              break;
            }
          }
          if (exists) break;
        }
      }
      if (!exists) jj_expentries.addElement(jj_expentry);
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  public ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[22];
    for (int i = 0; i < 22; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 6; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 22; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  final public void enable_tracing() {
  }

  final public void disable_tracing() {
  }

  final private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 6; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  final private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

        }