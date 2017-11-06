<?php

//EAACEdEose0cBAEWD19buSKljPP42rzF6nldBwn8WZCyWVARNMcjRLH1JvgMRdCq4FZCZAyZCykvK5DduYY0xibVPgdVkmIZCqwaXWZAjFyqcP6b0NNr2aHbHh0FoFkpgOdN8avjBQ1942ZCYlTvRZAWCixYpxMMh4GP9uRwTzRLWZBpl93AyW2ADGdPBQG8Gfp0EZD
//EAAYTP7gWvQUBAHLUFPvDNC0PwYIZAWpZC4VMWV5JCXbMf1EGo9aBjP09teYhsfZBIapjnmqVv6bikBCQQUYr0AcsqCJmqsj16x0FXMnlpwUpZA2quKV649ZB1Fa4pPxhIOXDEfM0ZB3ZAwcWGwfdoW42UfLvdQ6OZAQuFO8wkVOOnO1vf0L3LQpuPlnMxH6EPTkZD

require_once __DIR__ . '/php-graph-sdk-5.0.0/php-graph-sdk-5.0.0/src/Facebook/autoload.php';
$fb = new Facebook\Facebook([
'app_id' => '1753341921646015',
'app_secret' => '4c6e2e9dc627403ec3936f2319543481',
'default_graph_version' => 'v2.8',
 ]);



/*require_once __DIR__ . '/php-graph-sdk-5.0.0/php-graph-sdk-5.0.0/src/Facebook/autoload.php';
$fb = new Facebook\Facebook([
'app_id' => '1710014252629253',
'app_secret' => 'e5478ee115d2795f0b480bc55d6b9c41',
'default_graph_version' => 'v2.8',
 ]);*/

 $keyword = null;
 $type = null;
 $id = null;


 if(isset($_POST["searchme"])){
   $keyword = $_POST["searchme"];
 }
 else{
  $keyword = null;
 }

 if(isset($_POST["type"])){
   $type = $_POST["type"];
 }
 else{
  $type = null;
 }

 if(isset($_POST["pressed_id"])){
   $id = $_POST["pressed_id"];
 }
 else{
  $id = null;
 }




//var_dump($tab);



//var_dump($next_prev_url);
$access_token = "EAAY6p7po1b8BAPqJZCGlIDzQhuZAkwmgZBTFhgfEKOEVOjAjIlxKTfFIHI8lJ1XvMFMP1T1Uxf70wMWZBmcXQwt3nYR60QCi0KrZC5fMjohU45rqfaeW37fvaGNZAYZCOpXj0ZB5h4L1cx4xEbjkY1HLBZCwC4JG12vEZD";
//$access_token = "EAACEdEose0cBACPxCIZCqypZBANBQQ5077iGxWMEU7LwE22HX1hd50zve7w2JgXMWZBqbGTz64hIPGi0hLUeHr7cvOVqFZCu8m5K1e08mI5hdZARNlruUkKs8fRYM4cGnA0c6NTjE7ZBtNx1vp5Q2PU4QI9zz5b6Ctrob6IQrsWsMpNFtG7Q0SREnBaFsplvcZD";
//$access_token = "EAAYTP7gWvQUBAHLUFPvDNC0PwYIZAWpZC4VMWV5JCXbMf1EGo9aBjP09teYhsfZBIapjnmqVv6bikBCQQUYr0AcsqCJmqsj16x0FXMnlpwUpZA2quKV649ZB1Fa4pPxhIOXDEfM0ZB3ZAwcWGwfdoW42UfLvdQ6OZAQuFO8wkVOOnO1vf0L3LQpuPlnMxH6EPTkZD";//EAAY6p7po1b8BAFECaZB3qsZCvPMZBTS3RRWEfMYL5v8b496Afk6518ymfsqCmiGJ5QlC1eW4fpYsateP49bLBtEwVZC61XPIyyT02TZBE6p2Gi3df6RjsakuFiychMzE8TO6ZBDY728lKoEVE4mLhWW9V013oM2S2K3mB9ZBCKnJK1HanEUKamPYm9JNZAbWIF4ZD";
//EAAY6p7po1b8BAPqJZCGlIDzQhuZAkwmgZBTFhgfEKOEVOjAjIlxKTfFIHI8lJ1XvMFMP1T1Uxf70wMWZBmcXQwt3nYR60QCi0KrZC5fMjohU45rqfaeW37fvaGNZAYZCOpXj0ZB5h4L1cx4xEbjkY1HLBZCwC4JG12vEZD
//EAACEdEose0cBACPxCIZCqypZBANBQQ5077iGxWMEU7LwE22HX1hd50zve7w2JgXMWZBqbGTz64hIPGi0hLUeHr7cvOVqFZCu8m5K1e08mI5hdZARNlruUkKs8fRYM4cGnA0c6NTjE7ZBtNx1vp5Q2PU4QI9zz5b6Ctrob6IQrsWsMpNFtG7Q0SREnBaFsplvcZD
$fb->setDefaultAccessToken($access_token);



    //if(isset($keyword) && isset($tab))
    if($keyword != null)
    {

      switch ($type) {
        case 'user': $url1 = "/search";
                     $url1.= "?q={$keyword}";
                     $url1.= "&type=user";
                     $url1.= "&fields=id,name,picture.width(700).height(700)";

                      try {
                      // Returns a `Facebook\FacebookResponse` object
                            $response = $fb->get($url1);
                          } catch(Facebook\Exceptions\FacebookResponseException $e) {
                            echo 'Graph returned an error: ' . $e->getMessage();
                            exit;
                          } catch(Facebook\Exceptions\FacebookSDKException $e) {
                            echo 'Facebook SDK returned an error: ' . $e->getMessage();
                            exit;
                          }
                          $user_data = $response->getDecodedBody();
                          echo json_encode($user_data);

                     break;

        case 'page':$url2 = "/search";
                    $url2.= "?q={$keyword}";
                    $url2.= "&type=page";
                    $url2.= "&fields=id,name,picture.width(700).height(700)";

                    try {
                    // Returns a `Facebook\FacebookResponse` object
                          $response = $fb->get($url2);
                        } catch(Facebook\Exceptions\FacebookResponseException $e) {
                          echo 'Graph returned an error: ' . $e->getMessage();
                          exit;
                        } catch(Facebook\Exceptions\FacebookSDKException $e) {
                          echo 'Facebook SDK returned an error: ' . $e->getMessage();
                          exit;
                        }
                    $page_data = $response->getDecodedBody();
                    echo json_encode($page_data);
                    break;

        case 'event':$url3 = "/search";
                    $url3.= "?q={$keyword}";
                    $url3.= "&type=event";
                    $url3.= "&fields=id,name,picture.width(700).height(700)";

                    try {
                    // Returns a `Facebook\FacebookResponse` object
                          $response = $fb->get($url3);
                        } catch(Facebook\Exceptions\FacebookResponseException $e) {
                          echo 'Graph returned an error: ' . $e->getMessage();
                          exit;
                        } catch(Facebook\Exceptions\FacebookSDKException $e) {
                          echo 'Facebook SDK returned an error: ' . $e->getMessage();
                          exit;
                        }
                    $event_data = $response->getDecodedBody();
                    echo json_encode($event_data);

                    break;
        case 'place':$url4 = "/search";
                      $url4.= "?q={$keyword}";
                      $url4.= "&type=place";
                      $url4.= "&fields=id,name,picture.width(700).height(700)";
                      //$url4.= "&center={$lat},{$lon}";

                      try {
                      // Returns a `Facebook\FacebookResponse` object
                            $response = $fb->get($url4);
                          } catch(Facebook\Exceptions\FacebookResponseException $e) {
                            echo 'Graph returned an error: ' . $e->getMessage();
                            exit;
                          } catch(Facebook\Exceptions\FacebookSDKException $e) {
                            echo 'Facebook SDK returned an error: ' . $e->getMessage();
                            exit;
                          }
                      $place_data = $response->getDecodedBody();
                      echo json_encode($place_data);

                    break;

        case 'group':$url5 = "/search";
                      $url5.= "?q={$keyword}";
                      $url5.= "&type=group";
                      $url5.= "&fields=id,name,picture.width(700).height(700)";

                      try {
                      // Returns a `Facebook\FacebookResponse` object
                            $response = $fb->get($url5);
                          } catch(Facebook\Exceptions\FacebookResponseException $e) {
                            echo 'Graph returned an error: ' . $e->getMessage();
                            exit;
                          } catch(Facebook\Exceptions\FacebookSDKException $e) {
                            echo 'Facebook SDK returned an error: ' . $e->getMessage();
                            exit;
                          }
                      $group_data = $response->getDecodedBody();
                      echo json_encode($group_data);

                    break;



      }

    }


      else
      if($id!=null){

          $url = "https://graph.facebook.com/v2.8/{$id}?fields=albums.limit(5){name,photos.limit(2){name,picture}},posts.limit(5)&access_token={$access_token}";

          $mutual = json_decode(file_get_contents($url));

          echo json_encode($mutual);
        }

        //echo "<script>alert('No hello');</script>";












        //echo json_encode(array($user_data,$page_data,$event_data,$place_data,$group_data));



















?>
