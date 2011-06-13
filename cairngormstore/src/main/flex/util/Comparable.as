package util
{
  /**
   * @version  $Revision: $
   */

  /**
   * Interface needed to implement by filter objects of Comparator.
   * @description
   * All filter objects need to implement the Comparable interface,
   * which forces objects to implement a getIdentifier method. The getIdentifier method shall
   * provide a unique identifier to each filter object.
   * @see Comparator
   */
  public interface Comparable
  {
    function get identifier() : String
  }
}
