/** AI description
 * Draft phase:
 *  - always pick the first card
 * Game phase:
 *  - do nothing (outputs single ';')
 */
class Player
{
  public static void main(String[] args)
  {
    for (int i=0; i < 30; i++)
      System.out.println(String.format("PICK 1"));

    while (true)
      System.out.println(";");
  }
}
